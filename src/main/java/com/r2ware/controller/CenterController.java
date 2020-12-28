package com.r2ware.controller;

import com.r2ware.repository.Users;
import com.r2ware.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CenterController {
    @Autowired
    private UsersRepository repository;

    @RequestMapping("/login")
    public String login(Users user, Model model) {
        System.out.println("User ID : " + user.getUserId() +
                ", Password : " + user.getPassword());

        Optional<Users> findUser = repository.findById(user.getUserId());
        if(findUser.isPresent()) {
             if( findUser.get().getPassword().equals(user.getPassword()) ) {
                 model.addAttribute("userId", user.getUserId());
                 return "welcome";
             }

             // wrong password
             return "redirect:/login.html";
        }

        // not exist user information in the Database
        return "redirect:/login.html";
    }

    @RequestMapping("/register")
    @ResponseBody
    public List<Users> register(Users user, Model model) {
        List<Users> users = new ArrayList<Users>();


        repository.save(user);
        users.addAll(repository.findAll());

        return users;
    }

}
