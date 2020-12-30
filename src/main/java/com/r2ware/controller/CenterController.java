package com.r2ware.controller;

import com.r2ware.repository.Users;
import com.r2ware.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CenterController {
    @Autowired
    private UsersRepository repository;

    @RequestMapping("/login")
    public String login(HttpServletRequest req, Users user, Model model) {
        System.out.println("User ID : " + user.getUserId() +
                ", Password : " + user.getPassword());

        Optional<Users> findUser = repository.findById(user.getUserId());
        if(findUser.isPresent()) {
             if( findUser.get().getPassword().equals(user.getPassword()) ) {
                 HttpSession session = req.getSession();
                 session.setAttribute("userid", user.getUserId());

                 model.addAttribute("userId", user.getUserId());
                 return "welcome";
             }

             // wrong password
             return "redirect:/login.html";
        }

        // not exist user information in the Database
        return "redirect:/login.html";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @RequestMapping("/register")
    @ResponseBody
    public List<Users> register(HttpServletRequest req, Users user, Model model) {
        List<Users> users = new ArrayList<Users>();

/*
        HttpSession session = req.getSession();
        if(user.getUserId().equals(session.getAttribute("userid"))) {
            System.out.println("You are already connected in this system.");
            users.addAll(repository.findAll());
            return users;
        }
*/
        System.out.println("userId[" + user.getUserId() + "]");

        if(user.getUserId() != null && user.getUserId().isEmpty() == false) {
            repository.save(user);
        }

        users.addAll(repository.findAll());


//        session.setAttribute("userid", user.getUserId());

//        System.out.println("Session Info : " + session.getAttribute("userid"));

        return users;
    }

}
