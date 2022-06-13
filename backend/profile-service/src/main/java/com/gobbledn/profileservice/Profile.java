package com.gobbledn.profileservice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
