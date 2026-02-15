package com.example.keepMates.post;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.keepMates.common.entity.PostEntity;
import com.example.keepMates.common.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 投稿機能:サービスの実装クラス
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	// 投稿取得用
	public PostResponseDTO getPosts(PostRequestDTO inDto) {

		List<PostEntity> entities;
		
		String age = inDto.getAge();

		try {
			// ageがnull、または空文字の時は全件取得
			if (age == null || age.isEmpty()) {
				entities = postRepository.findAllByOrderByIdDesc();
			} else {
				// ageがある時はその年齢で絞り込み
				entities = postRepository.findByAgeOrderByIdDesc(age);
			}

			// DTOへの詰め替え処理
			List<PostListDTO> dtoList = entities.stream().map(entity -> {
				PostListDTO dto = new PostListDTO();
				dto.setId(entity.getId());
				dto.setUserId(entity.getUserId());
				dto.setImageUrl(entity.getImageUrl());
				dto.setCaption(entity.getCaption());
				dto.setAge(entity.getAge());
				return dto;
			}).collect(Collectors.toList());

			PostResponseDTO response = new PostResponseDTO();
			response.setResultCode("00");
			response.setPostList(dtoList);
			return response;

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	// 登録用
	public PostResponseDTO create(PostRequestDTO inDto) {
		
		PostEntity entity = new PostEntity();
		entity.setUserId(inDto.getUserId());
		entity.setImageUrl(inDto.getImageUrl());
		entity.setCaption(inDto.getCaption());
		entity.setAge(inDto.getAge());

		postRepository.save(entity);
		
		// DTOからEntityへ詰め替え
		PostResponseDTO response = new PostResponseDTO();
		response.setResultCode("00");
		return response;
	}

}
