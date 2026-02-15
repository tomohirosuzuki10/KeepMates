package com.example.keepMates;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.keepMates.common.entity.PostEntity;
import com.example.keepMates.common.entity.UserEntity;
import com.example.keepMates.common.repository.PostRepository;
import com.example.keepMates.common.repository.UserRepository;
import com.example.keepMates.post.PostRequestDTO;
import com.example.keepMates.post.PostResponseDTO;
import com.example.keepMates.post.PostService;

@SpringBootTest // Springの機能をテストで使うためのアノテーション
public class PostServiceImplTest {

	@Autowired
	private PostService postService; // テスト対象のサービス

	@Autowired
	private PostRepository postRepository; // 本物のリポジトリ

	@Autowired
	private UserRepository userRepository; // 本物のリポジトリ

	@Test
	@Transactional
	void 写真取得_年齢指定なし() {

		// 1. 準備（DBにテストデータを入れる）
		UserEntity user1 = new UserEntity();
		user1.setId(123);
		user1.setUserName("DB登録1");
		user1.setPassword("dbpass1");
		user1.setBirthDate(LocalDate.of(2024, 5, 20));

		UserEntity user2 = new UserEntity();
		user2.setId(456);
		user2.setUserName("DB登録2");
		user2.setPassword("dbpass2");
		user2.setBirthDate(LocalDate.of(2023, 3, 10));

		userRepository.saveAllAndFlush(List.of(user1, user2));

		PostEntity post1 = new PostEntity();
		post1.setUserId(123);
		post1.setImageUrl("url1");
		post1.setCaption("caption1");
		post1.setAge("20");
		postRepository.saveAndFlush(post1);

		PostEntity post2 = new PostEntity();
		post2.setUserId(456);
		post2.setImageUrl("url2");
		post2.setCaption("caption2");
		post2.setAge("30");
		postRepository.saveAndFlush(post2);

		// 2. 実行（Serviceのメソッドを普通に呼ぶ）
		PostRequestDTO request = new PostRequestDTO();
		// 年齢指定なしなので request.setAge(null) と同じ
		PostResponseDTO result = postService.getPosts(request);

		// 3. 検証
		assertEquals("00", result.getResultCode());
		assertEquals(2, result.getPostList().size());
		// 登録した順番と逆の順番で返ってきているか確認
		assertEquals("30", result.getPostList().get(0).getAge());
		assertEquals("url2", result.getPostList().get(0).getImageUrl());
		assertEquals("20", result.getPostList().get(1).getAge());
		assertEquals("url1", result.getPostList().get(1).getImageUrl());
	}

	@Test
	@Transactional
	void 写真取得_年齢指定あり() {
		// 準備（DBにテストデータを入れる）
		UserEntity user1 = new UserEntity();
		user1.setId(123);
		user1.setUserName("DB登録1");
		user1.setPassword("dbpass1");
		user1.setBirthDate(LocalDate.of(2024, 5, 20));

		UserEntity user2 = new UserEntity();
		user2.setId(456);
		user2.setUserName("DB登録2");
		user2.setPassword("dbpass2");
		user2.setBirthDate(LocalDate.of(2023, 3, 10));

		userRepository.saveAllAndFlush(List.of(user1, user2));

		PostEntity post1 = new PostEntity();
		post1.setUserId(123);
		post1.setImageUrl("url1");
		post1.setCaption("caption1");
		post1.setAge("20");
		postRepository.saveAndFlush(post1);

		PostEntity post2 = new PostEntity();
		post2.setUserId(456);
		post2.setImageUrl("url2");
		post2.setCaption("caption2");
		post2.setAge("30");
		postRepository.saveAndFlush(post2);

		// 実行（年齢で絞り込み）
		PostRequestDTO request = new PostRequestDTO();
		request.setAge("20");
		PostResponseDTO result = postService.getPosts(request);

		// 検証
		// 結果コードと件数を確認
		assertEquals("00", result.getResultCode());
		// 年齢20の投稿だけが返ってきているか確認
		assertEquals(1, result.getPostList().size());
		// 絞り込んだ年齢の投稿が返ってきているか確認
		assertEquals("20", result.getPostList().get(0).getAge());
		assertEquals("url1", result.getPostList().get(0).getImageUrl());
	}

	@Test
	@Transactional
	void 投稿登録テスト() {
		// 登録前の件数
		long before = postRepository.count();

		UserEntity user1 = new UserEntity();
		user1.setId(123);
		user1.setUserName("DB登録1");
		user1.setPassword("dbpass1");
		user1.setBirthDate(LocalDate.of(2024, 5, 20));

		UserEntity user2 = new UserEntity();
		user2.setId(456);
		user2.setUserName("DB登録2");
		user2.setPassword("dbpass2");
		user2.setBirthDate(LocalDate.of(2023, 3, 10));

		userRepository.saveAllAndFlush(List.of(user1, user2));

		// リクエスト作成
		PostRequestDTO request = new PostRequestDTO();
		request.setUserId(456);
		request.setImageUrl("newUrl");
		request.setCaption("newCaption");
		request.setAge("25");

		// 実行
		PostResponseDTO response = postService.create(request);

		// 検証
		assertEquals("00", response.getResultCode());
		long after = postRepository.count();
		assertEquals(before + 1, after);

		// 最新の投稿が登録された内容か確認
		PostEntity saved = postRepository.findAllByOrderByIdDesc().get(0);
		assertEquals(456, saved.getUserId());
		assertEquals("newUrl", saved.getImageUrl());
		assertEquals("newCaption", saved.getCaption());
		assertEquals("25", saved.getAge());
	}

}