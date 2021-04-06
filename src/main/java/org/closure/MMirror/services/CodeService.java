package org.closure.MMirror.services;

import java.util.Optional;
import java.time.Instant;

import org.closure.MMirror.Exceptions.CodeException;
import org.closure.MMirror.entities.Code;
import org.closure.MMirror.repositories.CodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CodeService {
    @Value("${closure.codeGenerationError}")
    private String errorValue;
    
    @Autowired
    private CodeRepo codeRepo;

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
}
