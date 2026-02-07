package com.example.keepMates.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PostListDTO {
	
	@JsonProperty("id")
    private Integer id;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("age")
    private String age;

}
