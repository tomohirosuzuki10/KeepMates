package com.example.keepMates.post;

import org.springframework.http.ResponseEntity;

public interface PostController {
	
	/*
	 * @param postRequestDto
	 * @return ResponseEntity<PostResponseDTO>
	 */
	ResponseEntity<PostResponseDTO> getPosts(PostRequestDTO postRequestDto);
	
	ResponseEntity<PostResponseDTO> create(PostRequestDTO postRequestDto);

}
