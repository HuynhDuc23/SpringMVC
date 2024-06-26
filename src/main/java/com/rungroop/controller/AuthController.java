package com.rungroop.controller;

import com.rungroop.dto.RegistrationDto;
import com.rungroop.models.UserEntity;
import com.rungroop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService ;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user =  new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result , Model model){
        UserEntity existsUserEmail = userService.findByEmail(user.getEmail());
        if(existsUserEmail != null && existsUserEmail.getEmail() != null && !existsUserEmail.getEmail().isEmpty()){
          //  result.reject("email" , "There is already a user registered with this email / user");
            return "redirect:/register?fail";
        }
        UserEntity existsUsername =  userService.findByUsername(user.getUsername());
        if(existsUsername != null && existsUsername.getUsername() != null && !existsUsername.getUsername().isEmpty()){
            //result.reject("username" , "There is already a user registered with username / user");
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register"; // ve trang
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
