package com.example.matrimony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.matrimony.domain.Profile;
import com.example.matrimony.repository.ProfileRepository;

@SuppressWarnings("unused")
@Controller
public class RegisterController {

    final ProfileRepository repo;

    RegisterController(ProfileRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/signup")
    public String displaySignup() {
        return "register.html";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Profile profile) {
        if (repo.existsByUsername(profile.getUsername())) {
            return "redirect:/register.html?error=exists";
        }
        repo.save(profile);
        return "redirect:/index.html";
    }
}
