package org.closure.MMirror.services;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

import org.closure.MMirror.Exceptions.CodeException;
import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.Code;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.repositories.CodeRepo;
import org.closure.MMirror.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CodeService {
    @Value("${closure.codeGenerationError}")
    private String errorValue;
    
    @Autowired
    private CodeRepo codeRepo;

    @Autowired 
    private UserRepo userRepo;
    
    public Long generateCode() throws Exception
    {
        Optional<Code> value = null;
        Long code = null;
        do{
            code = Long.valueOf((long)Math.random()*1000);
            value = codeRepo.findByCode(code);

        }while(value.isPresent());
        codeRepo.save(new Code().code(code).created_at(Instant.now()).expire_at(Instant.now().plusSeconds(180)));
        return codeRepo.findByCode(code).orElseThrow(
            ()-> new CodeException(errorValue)).getCode();
        
    }

    public boolean deleteCodes()
    {
        List<Code> codes = codeRepo.findAll();
        codes = codes.stream().filter((code) -> code.getExpire_at().isAfter(Instant.now())).toList();
        codeRepo.deleteAll();
        codeRepo.saveAll(codes);
        return true;
    }

    public boolean occupidCode(String userID, String code_value) throws CodeException,UserException
    {
        Optional<Code> code = null;
        User user = null;
        if((code = codeRepo.findById(Long.valueOf(code_value))).isPresent() && (user = userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id ..."))).getCode() != null)
        {
            if(code.get().getUser() == null && code.get().getExpire_at().isAfter(Instant.now()))
            {
            user = user.code(code.get());
            userRepo.save(user);
            }
            else
            {
                throw new CodeException("this code is expired or used before ...");
            }
        }
        else
        {
            throw new CodeException("can't find this code.");
        }
        return true;
    }
}
