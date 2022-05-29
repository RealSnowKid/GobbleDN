package com.gobbledn.profileservice;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROFILE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private String role = "user";
    private Integer post_count = 0;
    private Integer follower_count = 0;
    @ElementCollection
    private List<Integer> followers_id;
}
