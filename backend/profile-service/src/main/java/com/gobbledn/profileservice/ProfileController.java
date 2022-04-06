package com.gobbledn.profileservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @RequestMapping("")
    public List<Profile> getProfiles() {
        return service.getProfile();
    }

    @PostMapping("/createProfile")
    public Profile createProfile(@RequestBody Profile profile) {
        return service.saveProfile(profile);
    }
}
