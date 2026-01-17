package com.example.keepMates.login;

/**
 * ログイン機能:サービスのインターフェース
 */
public interface LoginService {
	/*
	 * @param loginRequestDto
	 * @return LoginResponseDTO
	 */
	LoginResponseDTO login(LoginRequestDTO loginRequestDto);

}
