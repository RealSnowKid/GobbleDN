package com.gobbledn.profileservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private int Id;
    private String username;
    private String email;
    private String role = "user";
    private Integer post_count = 0;
    private Integer follower_count = 0;
    private List<Integer> followers_id;
}
