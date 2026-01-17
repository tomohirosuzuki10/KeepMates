package com.example.keepMates.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.keepMates.common.entity.UserEntity;
import com.example.keepMates.common.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	public UserRepository userRepository;
	
	/**
	 * ログインリクエストを処理する
	 * @param loginRequestDto
	 * @return LoginResponseDTO
	 */
	public LoginResponseDTO login(LoginRequestDTO loginRequestDto) {
		
		try {
			UserEntity userEntity = userRepository.findByIdAndPassword(
					loginRequestDto.getUserId(),
					loginRequestDto.getPassword());
			
			LoginResponseDTO responseDto = new LoginResponseDTO();
			if (userEntity == null) {
				// ユーザーが見つからなかった場合、エラーコードを設定
				responseDto.setResultCode("01");
				log.info("ログイン処理終了:ユーザーが見つかりません");	
				return responseDto;
			}
			// ユーザーが見つかった場合、成功コードとユーザー名を設定
			responseDto.setResultCode("00");
			responseDto.setUserName(userEntity.getUserName());
			log.info("ログイン処理終了:正常終了");
			return responseDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
