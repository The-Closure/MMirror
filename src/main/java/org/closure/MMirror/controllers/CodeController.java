package org.closure.MMirror.controllers;

import org.closure.MMirror.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class CodeController {
    
    @Autowired
    private CodeService codeService;

    @PostMapping(value="/generatecode")
    @ResponseBody
    public ResponseEntity<String> genreateCode() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(codeService.generateCode());            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    
    }

    
    
}
