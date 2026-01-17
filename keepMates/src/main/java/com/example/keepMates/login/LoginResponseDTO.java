package com.example.keepMates.login;

import lombok.Data;

/**
 * ログイン機能:レスポンスDTO
 */
@Data
public class LoginResponseDTO {
	
	// 処理結果コード
	private String ResultCode;
	
	// ユーザー名
	private String userName;
	
}
