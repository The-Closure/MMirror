package org.closure.MMirror.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.closure.MMirror.Exceptions.CodeException;
import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.Code;
import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.CodeDto;
import org.closure.MMirror.models.EventDto;
import org.closure.MMirror.models.UserDto;
import org.closure.MMirror.repositories.EventRepo;
import org.closure.MMirror.repositories.UserRepo;
import org.closure.MMirror.services.CodeService;
import org.closure.MMirror.services.EventService;
import org.closure.MMirror.services.IdGeneration;
import org.closure.MMirror.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.events.EventException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class ViewController {
    @Autowired
    private UserRepo userRepo;

    @Autowired 
    private UserService UserService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CodeService codeService;
     
    @GetMapping("/")
    public String viewHomePage(User user, HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("clientID") != null)
            try {
                response.sendRedirect("/events");
            } catch (IOException e) {
                return "index";
            }
        return "index";
    }
    @PostMapping("/registeruser")
    public String register(User user, HttpServletRequest request, Model model)
    {
        user = user.created_at(Instant.now()).id(IdGeneration.getNextRandomString()).is_in(true).is_active(false).google_account(false);
       
        UserService.addUser(new UserDto().name(user.getName()).email(user.getEmail()).password(user.getPassword()).id(user.getId()));
        request.getSession().setAttribute("clientID", user.getId());
        model.addAttribute("code", new CodeDto());
        return "redirect:/logout";
    }

    @GetMapping("/verfication_code")
    public String verfication_code(Model model, HttpServletRequest request)
    { 
    if(request.getSession().getAttribute("clientID") != null){
        System.out.println(request.getSession().getAttribute("clientID")+"@#@#@#");
        System.out.println(userRepo.findById(request.getSession().getAttribute("clientID")+"$$$$"));
        model.addAttribute("code", new CodeDto());
        return "verfication_code";}else{
            model.addAttribute("user", new User());
            return "index";}
    }

    @PostMapping(value="/occupid")
    public void occupidCode(CodeDto code, HttpServletResponse response, HttpServletRequest request) {
        try {
            System.out.println("user id : "+request.getSession().getAttribute("clientID")+ " code id : "+code);
            if(codeService.occupidCode(request.getSession().getAttribute("clientID")+"", code.getCode()))
            {
                response.sendRedirect("/events");
            }
        } catch (UserException | CodeException | IOException e) {
            try {
                System.out.println(e.getMessage()+"%%%");
                request.getSession().setAttribute("codeError", "enter valid code");
                response.sendRedirect("/verfication_code");
            } catch (IOException e1) {
                
            }
        }
    }


//     @GetMapping("/register")
//     public String showRegistrationForm(Model model) {
//         model.addAttribute("user", new User());
        
//         return "signup_form";
//     }
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("user", new User());
        if(request.getSession().getAttribute("clientID") != null){
            try {
                response.sendRedirect("/events");
            } catch (IOException e) {
                return "home";    
            }
            return "/home";}
        else return "login";
    }

    @PostMapping(value="/login_process")
    public String loginProcess(User user, HttpServletRequest request, HttpServletResponse response,Model model) {
        UserDto dto = new UserDto();
        try {
            dto = UserService.signin(user.getEmail(), user.getPassword());
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "home";
        }
        request.getSession().setAttribute("clientID", dto.getId());
        if(dto.getIs_active())
        return "redirect:/events";
        else return "home";
    }
    
    @GetMapping(value="/events")
    public String getMethodName( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if(request.getSession().getAttribute("clientID") != null)
       { 
           String clientID = ""+request.getSession().getAttribute("clientID");
        try {
            List<EventDto> dto;
            dto = eventService.getEvents(clientID);
            model.addAttribute("events", dto);
            dto.stream().forEach(System.out::println);
            return "events";
        }   catch(UserException e){
            model.addAttribute("error", e.getMessage());
            return "home";
        }
        }
        else {
            model.addAttribute("user", new User());
            response.sendRedirect("/");
            return "index";}
    }
    

//     @GetMapping("/home")
//     public String Home(Model model, HttpSession session) {
//         System.out.println("home : "+ session.getAttribute("clientID"));
//         model.addAttribute("event", new Event().summery("summery"));
        
//         return "home";
//     }
    
//     @PostMapping("/process_register")
// 	public String processRegister(User user, HttpServletRequest request) {
// 		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// 		String encodedPassword = passwordEncoder.encode(user.getPassword());
// 		user.setPassword(encodedPassword);
//         user.setId(IdGeneration.getNextRandomString());
//         user.setIs_active(false);
//         user.setIs_in(false);
//         user.setGoogle_account(false);
//         user.setCreated_at(Instant.now());
// 		userRepo.save(user);
        
// 		request.getSession().setAttribute("clientID", user.getId());
// 		return "register_success";
// 	 }

//      @GetMapping(value="/destroy")
//      public String destroySession(HttpServletRequest request) {
//          request.getSession().invalidate();
//          return "index";
//      }
//      @ModelAttribute("user")
// public User populateTypes(HttpSession session) {
//     if(session.getAttribute("clientID") != null)
//         return userRepo.findById((String)session.getAttribute("clientID")).orElseThrow(()-> new UserException("no user with this id"));
//     else return new User();
//     }
}