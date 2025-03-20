package com.nitish.springsecurityldap.Contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Security LDAP";
    }
}
