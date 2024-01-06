package com.workintech.controller;

import com.workintech.dto.RegistrationMember;
import com.workintech.entity.Member;
import com.workintech.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationMember registrationMember) {
        authenticationService.register(registrationMember.email(), registrationMember.password());
        return registrationMember.email();
    }

}
