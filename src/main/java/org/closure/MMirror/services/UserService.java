package org.closure.MMirror.services;

import java.time.Instant;

import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.MirrorDto;
import org.closure.MMirror.models.UserDto;
import org.closure.MMirror.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    public UserDto addUser(UserDto user) throws UserException {
        if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
            User entity = new User().email(user.getEmail()).name(user.getName()).password(user.getPassword())
                    .google_account(false).id(IdGeneration.getNextRandomString()).created_at(Instant.now()).is_in(true)
                    .is_active(false);
            entity = userRepo.save(entity);
            sendEmail(entity.getId(), entity.getEmail());
            // TODO : ACTIVE IT IN PRODUCTION
            return new UserDto().created_at(entity.getCreated_at()).email(entity.getEmail()).is_active(false)
                    .password(entity.getPassword()).name(entity.getName())
                    .password(entity.getPassword()).id(entity.getId());

        } else {
            throw new UserException("this email is already exist ...");
        }

    }

    public UserDto verifyAccount(String userID) throws UserException {
        User user = userRepo.findById(userID).orElseThrow(() -> new UserException("no user with this id ..."));
        if (!user.getIs_active())
            user.setIs_active(true);
        else
            throw new UserException("this user is already activated");
        user = userRepo.save(user);
        return new UserDto().created_at(user.getCreated_at()).email(user.getEmail()).is_active(user.getIs_active())
                .password(user.getPassword()).name(user.getName()).id(user.getId()).password(user.getPassword());
    }

    public UserDto signin(String email, String password) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserException("no user with this email ..."));
        if (user.getPassword().equals(password))
            user.setIs_in(true);
        else
            throw new UserException("wrong password ...");
        if (!user.getIs_active())
            throw new UserException("please visit your email and verify your emial");
        user = userRepo.save(user);
        return new UserDto().created_at(user.getCreated_at()).email(user.getEmail()).is_active(user.getIs_active())
                .password(user.getPassword()).name(user.getName()).id(user.getId()).password(user.getPassword());

    }

    public boolean signout(String userID) {
        User user = userRepo.findById(userID).orElseThrow(() -> new UserException("no user with this id ..."));
        user.setIs_in(false);
        user = userRepo.save(user);
        return !user.getIs_in();
    }

    public void sendEmail(String userID, String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("support@smart-mira.com");
        msg.setTo(email);

        msg.setSubject("Verfication mail (Magic Mirror)");
        msg.setText(
                "thanks for joining our platform \nplease verify your account by this link : http://magicm.hi-do.eu:8080/api/v2/users/home/verifyaccount/"
                        + userID);

        javaMailSender.send(msg);
    }

    public boolean isLinkedWithMirror(String userId)
    {
        System.out.println(userRepo.findById(userId).get().getMirror() != null);
        return userRepo.findById(userId).get().getMirror() != null;
    }

    public MirrorDto getUserIdWithMirrorId(String userId)
    {
        return new MirrorDto().userId(userId).id(userRepo.findById(userId).get().getMirror().getId());
    }
}
