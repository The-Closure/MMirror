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
    
    public String generateCode() throws CodeException
    {
        Optional<Code> value = null;
        String code = null;
        do{
            code = Double.valueOf((double)Math.random()*10000).longValue()+"";
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
        if((code = codeRepo.findById(code_value)).isPresent())
        {
            if(code.get().getUser() == null && code.get().getExpire_at().isAfter(Instant.now()))
            {
            user = (userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id")));
            Code entity = code.get();
            entity.setUser(user);
            codeRepo.save(entity);
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
