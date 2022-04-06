package com.gobbledn.postservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public Post getPostById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Post savePost(Post post) {
        return repository.save(post);
    }

    public void deletePostById(Integer id) {
        repository.deleteById(id);
    }

}
