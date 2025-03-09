package com.myCho.springStudy250303.controller;

import com.myCho.springStudy250303.model.User;
import com.myCho.springStudy250303.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String join() {
        return "account/register";
    }

    @PostMapping("/register")
    public String joinProcess(@ModelAttribute User user) {
        boardService.registerProcess(user);
        return "redirect:/account/login";
    }
}
