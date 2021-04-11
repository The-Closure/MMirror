package org.closure.MMirror.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.oauth2.sdk.id.ClientID;

import org.closure.MMirror.Exceptions.CodeException;
import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.Code;
import org.closure.MMirror.models.CodeDto;
import org.closure.MMirror.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
