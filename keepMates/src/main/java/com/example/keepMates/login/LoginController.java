package com.example.keepMates.login;

import org.springframework.http.ResponseEntity;

/**
 * ログイン機能:コントローラーのインターフェース
 */
public interface LoginController {
	
	/*
	 * @param loginRequestDto
	 * @return ResponseEntity<?>
	 */
	ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDto);

}
