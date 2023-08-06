package com.kakaoSpring.controller;

import com.kakaoSpring.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @GetMapping
    public String test(){
        return "home";
    }

    @PostMapping(value="android")
    @ResponseBody
    public String androidResponse(@RequestBody User user) {

        System.out.println("Connection from Android");
        System.out.println("name: " + user.getName() + ", email: " + user.getEmail());

        return "1";
    }

}