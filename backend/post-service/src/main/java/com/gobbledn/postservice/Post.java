package com.gobbledn.postservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private String Id;
    private Integer userId;
    private String content;
    private Integer likes;
    private Integer dislikes;
}
