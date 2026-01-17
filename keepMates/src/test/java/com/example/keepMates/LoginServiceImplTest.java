package com.example.keepMates;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keepMates.common.entity.UserEntity;
import com.example.keepMates.common.repository.UserRepository;
import com.example.keepMates.login.LoginRequestDTO;
import com.example.keepMates.login.LoginResponseDTO;
import com.example.keepMates.login.LoginService;

@SpringBootTest // Springの機能をテストで使うためのアノテーション
public class LoginServiceImplTest {
	
	@Autowired
    private LoginService loginService; // テスト対象のサービス
	
	@Autowired
	private UserRepository userRepository; // 本物のリポジトリ
	
	@Test
	@Transactional
	void ログイン成功_本物DB() {
		UserEntity user = new UserEntity();
		user.setId(123);
		user.setUserName("DB登録");
		user.setPassword("dbpass");
		user.setBirthDate(LocalDate.of(2024, 5, 20)); // 2024年5月20日
		userRepository.saveAndFlush(user); // DBに保存して即反映

		// 2. 実行（Serviceのメソッドを普通に呼ぶ）
		LoginRequestDTO request = new LoginRequestDTO();
		request.setUserId(123);
		request.setPassword("dbpass");
		LoginResponseDTO result = loginService.login(request);

		// 3. 検証
		// ユーザー名を確認
		assertEquals("DB登録", result.getUserName());
		// 正常終了コードを確認
		assertEquals("00", result.getResultCode());
	}
	@Test
	@Transactional
	void ログイン_本物DB不一致() {
		UserEntity user = new UserEntity();
		user.setId(123);
		user.setUserName("DB登録");
		user.setPassword("dbpass");
		user.setBirthDate(LocalDate.of(2024, 5, 20)); // 2024年5月20日
		userRepository.saveAndFlush(user); // DBに保存して即反映

		// 2. 実行（Serviceのメソッドを普通に呼ぶ）
		LoginRequestDTO request = new LoginRequestDTO();
		request.setUserId(123);
		request.setPassword("dbpass123");
		LoginResponseDTO result = loginService.login(request);

		// 3. 検証
		// 正常終了コードを確認
		assertEquals("01", result.getResultCode());
	}
}
	
//	@Test
//    void ログイン成功_モック() {// ↓勉強用モックを使ったログインテスト
		
//		@MockitoBean
//		private UserRepository mockUserRepository; // モック化するリポジトリ
		
//		// 1. 準備（偽の戻り値を作る）
//        UserEntity mockUser = new UserEntity();
//        mockUser.setId(1);
//        mockUser.setUserName("モック");
//        mockUser.setPassword("pass");
//		
//        // 2. 「覚え込ませる」 (スタブ化)
//        // IDが1、パスワードが "pass" で呼ばれたら mockUser を返す
//        Mockito.when(mockUserRepository.findByIdAndPassword(1, "pass"))
//               .thenReturn(mockUser);
//
//        // 3. 実行（Serviceのメソッドを普通に呼ぶ）
//        LoginRequestDTO request = new LoginRequestDTO();
//        request.setUserId(1);
//        request.setPassword("pass");
//        LoginResponseDTO result = loginService.login(request);
//
//        // 4. 検証
//        // ユーザー名を確認
//        assertEquals("モック", result.getUserName());
//        // 正常終了コードを確認
//        assertEquals("00", result.getResultCode());
//	}

