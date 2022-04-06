package com.gobbledn.profileservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public List<Profile> getProfile() {
        return repository.findAll();
    }

    public Profile saveProfile(Profile profile) {
        return repository.save(profile);
    }
}
