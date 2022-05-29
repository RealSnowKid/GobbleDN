package com.gobbledn.profileservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("")
    public List<Profile> getProfiles() {
        return service.getProfile();
    }

    @GetMapping("/{id}")
    public Optional<Profile> getProfileById(@PathVariable(value="id") Integer id) {return service.getProfileById(id);}

    @PostMapping("/createProfile")
    public Profile createProfile(@RequestBody Profile profile) {
        return service.saveProfile(profile);
    }

    @PutMapping("/follow/{id}")
    public ResponseEntity<String> followProfile(@PathVariable(value="id") Integer id, @RequestBody Map<String, Integer> json) {
        Optional<Profile> profileData = service.getProfileById(id);
        if (profileData.isPresent()) {
            Profile newProfile = profileData.get();
            List<Integer> followers = newProfile.getFollowers_id();
            followers.add(json.get("followerId"));
            newProfile.setFollowers_id(followers);
            newProfile.setFollower_count(followers.size());
            service.saveProfile(newProfile);
            return new ResponseEntity<>("Successfully followed user with ID " + newProfile.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
