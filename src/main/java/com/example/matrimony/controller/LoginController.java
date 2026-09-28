package com.example.matrimony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matrimony.domain.Profile;
import com.example.matrimony.repository.ProfileRepository;

@SuppressWarnings("unused")
@Controller
public class LoginController {

    final ProfileRepository repo;

    LoginController(ProfileRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/signin")
    public String showLoginPage() {
        return "login.html";
    }

    @PostMapping("/signin")
    public String login(@RequestParam String username, @RequestParam String password) {
        Profile entity = repo.findByUsernameAndPassword(username, password);
        if (entity == null) {
            return "redirect:/login.html?error=1";
        }
        return "redirect:/dashboard.html?user=" + entity.getUsername();
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/index.html";
    }
}
