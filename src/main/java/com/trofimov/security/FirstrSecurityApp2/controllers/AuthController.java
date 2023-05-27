package com.trofimov.security.FirstrSecurityApp2.controllers;

import com.trofimov.security.FirstrSecurityApp2.models.Person;
import com.trofimov.security.FirstrSecurityApp2.services.RegisterService;
import com.trofimov.security.FirstrSecurityApp2.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;

    private final RegisterService registerService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegisterService registerService) {
        this.personValidator = personValidator;
        this.registerService = registerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        registerService.register(person);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        return "redirect:/auth/login";
    }

}
