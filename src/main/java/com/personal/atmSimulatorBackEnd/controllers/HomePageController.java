package com.personal.atmSimulatorBackEnd.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping(value = "/")
    public String welcomePage() {
        return "This is our atm simulator REST api !";
    }
}
