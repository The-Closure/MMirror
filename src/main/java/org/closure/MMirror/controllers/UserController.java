package org.closure.MMirror.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.models.UserDto;
import org.closure.MMirror.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@RequestMapping(path = {"/api/v2/users","/"})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/register")
    public ResponseEntity<Object> postMethodName
    (
        @RequestBody UserDto request
    ) 
    {    try {
        
        request = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(request.toString());
        
    } catch (UserException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    
    }
    }
    
    @GetMapping(value="/home/verifyaccount/{id}")
    public ResponseEntity<String> verifyAccount(@PathVariable(name = "id") String id, HttpServletResponse response,HttpSession session) {
       try {
        UserDto dto = userService.verifyAccount(id);
        session.setAttribute("clientID", dto.getId());
        System.out.println(session.getAttribute("clientID")+"****");
        response.sendRedirect("/events");
        return ResponseEntity.status(HttpStatus.OK).body(userService.verifyAccount(id).toString());   
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());   
       } 
    }

    @PostMapping(value="/signin")
    public ResponseEntity<String> signIn(@RequestBody UserDto request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.signin(request.getEmail(), request.getPassword()).toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping(value="/signout")
    public ResponseEntity<String> signout(@RequestBody UserDto request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.signout(request.getId())+"");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping("/home")
    @ResponseBody
    public String testtest(HttpServletRequest request ,HttpServletResponse response,@RequestParam(name = "name" ,defaultValue = "user") String name){
       request.getAttributeNames().asIterator().forEachRemaining(System.out::println);
        return "hello123 : "+name;
    }

    
}
