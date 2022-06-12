package com.gobbledn.profileservice;

import java.util.List;
import java.util.Optional;

import org.hibernate.procedure.NoSuchParameterException;
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

    public Optional<Profile> getProfileById(Integer Id) { return repository.findById(Id); }

    public Integer increaseProfilePosts(String Id) {
        int id = Integer.parseInt(Id);
        Optional<Profile> updateProfile = repository.findById(id);
        if (updateProfile.isPresent()) {
            updateProfile.get().setPost_count(updateProfile.get().getPost_count() + 1);
            repository.save(updateProfile.get());
            return updateProfile.get().getPost_count();
        }
       else {
            throw new NoSuchParameterException(String.format("User with ID %id not found", Id));
        }
    }
}

