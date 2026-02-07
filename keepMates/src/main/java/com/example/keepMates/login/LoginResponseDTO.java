package com.example.keepMates.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * ログイン機能:レスポンスDTO
 */
@Data
public class LoginResponseDTO {
	
	// 処理結果コード
	@JsonProperty("resultCode")
	private String resultCode;
	
	// ユーザー名
	@JsonProperty("userName")
	private String userName;
	
}
