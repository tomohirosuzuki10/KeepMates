package com.example.keepMates.post;

public interface PostService {
	
	/*
	 * @param postRequestDto
	 * @return PostResponseDTO
	 */
	PostResponseDTO getPosts(PostRequestDTO postRequestDto);
	
	PostResponseDTO create(PostRequestDTO postRequestDto);

}
