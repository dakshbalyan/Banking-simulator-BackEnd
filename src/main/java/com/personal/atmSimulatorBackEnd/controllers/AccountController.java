package com.personal.atmSimulatorBackEnd.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class AccountController {

    // Logout function to HomePage

    @GetMapping("account/logOut")
    public String logOut() {
        return "Back to the home page !";
    }

}
