package com.gobbledn.postservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("")
    public List<Post> getPosts() {
        return service.getPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        return service.getPostById(postId);
    }

    @PostMapping("/createPost")
    public Post createPost(@RequestBody Post post) {
        streamBridge.send("postCreated-out-0", post.getUserId());
        return service.savePost(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable String postId) {
        service.deletePostById(postId);
    }
}
