package com.gobbledn.postservice;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public Post getPostById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Post savePost(Post post) {
        return repository.save(post);
    }

    public void deletePostById(String id) {
        repository.deleteById(id);
    }

}
