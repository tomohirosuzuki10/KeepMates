package com.example.keepMates.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class PostResponseDTO {
	
	// 処理結果コード
	@JsonProperty("resultCode")
	private String resultCode;
	
	// 投稿リスト
	@JsonProperty("postList")
	private List<PostListDTO> postList;

}
