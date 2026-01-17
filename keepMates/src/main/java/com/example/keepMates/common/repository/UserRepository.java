package com.example.keepMates.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.keepMates.common.entity.UserEntity;
/**
 * ユーザーリポジトリ
 */

@Repository //DBアクセスを行うクラスであることを示すアノテーション
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    // ログイン「ユーザー名取得」
	UserEntity findByIdAndPassword(Integer id,String password);
     
}