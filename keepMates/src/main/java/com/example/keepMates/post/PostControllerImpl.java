package com.example.keepMates.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//ログイン機能:コントローラーの実装クラス
@RestController //自動的にJSON形式でレスポンスを返すことを示すアノテーション
public class PostControllerImpl implements PostController {

	@Autowired
	private PostService postService;

	// --- 取得（検索） ---
	// age ひとつだけをパラメータで受け取る
	@GetMapping("/posts")
	public ResponseEntity<PostResponseDTO> getPosts(PostRequestDTO dto) {

		// 写真取得サービスを呼び出してレスポンスDTOを取得
		try {
			log.info("写真取得処理開始");
			PostResponseDTO responseDto = postService.getPosts(dto);

			// レスポンスDTOを含むResponseEntityを返す
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			log.info("写真取得処理異常終了");
			// エラーが発生した場合、500 Internal Server Errorを返す
			return ResponseEntity.internalServerError().body(null);
		}
	}

	// --- 登録 ---
	// 5項目入った DTO を JSON で受け取る
	@PostMapping("/posts")
	public ResponseEntity<PostResponseDTO> create(PostRequestDTO dto) {

		// 写真登録サービスを呼び出してレスポンスDTOを取得
		try {
			log.info("写真取得処理開始");
			PostResponseDTO responseDto = postService.create(dto);

			// レスポンスDTOを含むResponseEntityを返す
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			log.info("写真取得処理異常終了");
			// エラーが発生した場合、500 Internal Server Errorを返す
			return ResponseEntity.internalServerError().body(null);
		}
    }
}
