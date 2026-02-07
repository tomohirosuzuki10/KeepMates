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
	
	List<PostEntity> findAllByOrderByIdDesc();
	
	List<PostEntity> findByAgeOrderByIdDesc(String age);
	
	PostEntity save(PostEntity postEntity);

}
