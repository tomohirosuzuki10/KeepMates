package com.example.keepMates.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// ログイン機能:コントローラーの実装クラス
@RestController //自動的にJSON形式でレスポンスを返すことを示すアノテーション
public class LoginControllerImpl implements LoginController {
	
	@Autowired // LoginServiceの実装クラスをSpringが自動で注入する
    private LoginService loginService;
	
	/**
     * ログインリクエストを処理する
     * 成功なら 200 OK + コード"00"と名前を返す
     */
	@Override
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto) {
		// ログインサービスを呼び出してレスポンスDTOを取得
		try {
			log.info("ログイン処理開始");
			LoginResponseDTO responseDto = loginService.login(loginRequestDto);
			
			// レスポンスDTOを含むResponseEntityを返す
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			log.info("ログイン処理異常終了");
			// エラーが発生した場合、500 Internal Server Errorを返す
			return ResponseEntity.internalServerError().body(null);
		}
	}
	

}
