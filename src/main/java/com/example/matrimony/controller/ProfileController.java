package com.example.matrimony.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.matrimony.domain.Profile;
import com.example.matrimony.repository.ProfileRepository;

@SuppressWarnings("unused")
@RestController
public class ProfileController {

    final ProfileRepository repo;

    ProfileController(ProfileRepository repo) {
        this.repo = repo;
    }

    // Browse/search other profiles. All filters are optional.
    @GetMapping("/api/profiles")
    public List<Profile> listProfiles(
            @RequestParam(required = false) String exclude,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String religion,
            @RequestParam(required = false) String city) {

        List<Profile> profiles = (exclude == null || exclude.isBlank())
                ? repo.findAll()
                : repo.findByUsernameNot(exclude);

        return profiles.stream()
                .filter(p -> gender == null || gender.isBlank() || gender.equalsIgnoreCase(p.getGender()))
                .filter(p -> religion == null || religion.isBlank() || religion.equalsIgnoreCase(p.getReligion()))
                .filter(p -> city == null || city.isBlank() || city.equalsIgnoreCase(p.getCity()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/profiles/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
