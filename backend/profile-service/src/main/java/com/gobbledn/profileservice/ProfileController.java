package com.gobbledn.profileservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Profile> getProfiles() {
        return service.getProfile();
    }

    @GetMapping("/{id}")
    public Optional<Profile> getProfileById(@PathVariable(value="id") Integer id) {return service.getProfileById(id);}

    @PostMapping("/createProfile")
    public ProfileDTO createProfile(@RequestBody ProfileDTO profileDTO) {
        Profile profile = convertToEntity(profileDTO);
        Profile createdProfile = service.saveProfile(profile);
        return convertToDTO(createdProfile);
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
            Optional<Profile> followerData = service.getProfileById(json.get("followerId"));
            String azureFunctionResponse = CallAzureFunction(profileData.get(), followerData.get());
            return new ResponseEntity<>("Successfully followed user with ID " + newProfile.getId() + "\r\nAzure Function Response: " + azureFunctionResponse,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private String CallAzureFunction(Profile user, Profile follower) {
        String uri = "https://gobbledntestazurefunction.azurewebsites.net/api/FollowTrigger";
        RestTemplate restTemplate = new RestTemplate();
        String username = user.getUsername();
        String followerUsername = follower.getUsername();
        uri = uri + String.format("?username=%s&follower=%s", username, followerUsername);
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    private ProfileDTO convertToDTO(Profile profile) {
        ProfileDTO profileDTO = modelMapper.map(profile, ProfileDTO.class);
        return profileDTO;
    }

    private Profile convertToEntity(ProfileDTO profileDTO) {
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        return profile;
    }
}
