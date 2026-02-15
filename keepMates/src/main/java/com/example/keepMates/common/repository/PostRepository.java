package com.example.keepMates.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keepMates.common.entity.PostEntity;

/**
 * 投稿リポジトリ
 */
@Repository //DBアクセスを行うクラスであることを示すアノテーション
public interface PostRepository extends JpaRepository<PostEntity, Integer>  {
	
	// 投稿をIDの降順で全件取得するメソッド
	List<PostEntity> findAllByOrderByIdDesc();
	
	// 年齢で絞り込んで投稿をIDの降順で取得するメソッド
	List<PostEntity> findByAgeOrderByIdDesc(String age);
	
	// 投稿を保存するメソッド
	PostEntity save(PostEntity postEntity);

}
