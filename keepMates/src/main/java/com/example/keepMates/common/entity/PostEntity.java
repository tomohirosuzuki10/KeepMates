package com.example.keepMates.common.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity //Spring Boot（JPA）にこのクラスがエンティティであることを示します。
@Table(name = "posts") //データベースの"users"テーブルにマッピングされることを指定します。
@Data //Lombokの@Dataアノテーションを使用して、ゲッター、セッター、toString、equals、hashCodeなどのメソッドを自動生成します。
public class PostEntity {
	
	@Id
	@Column(name = "id")
	private Integer id;	 // 投稿ID (Integer / PK)
	
	@Column(name = "user_id")
	private Integer userId; // ユーザーID (Integer / FK)

	@Column(name = "image_url")
	private String imageUrl; // 画像URL (String)
	
	@Column(name = "caption")
	private String caption; // キャプション (String)

	@Column(name = "age")
	private String age; // 年齢 (String)

	@Column(name = "created_at")
	private Timestamp createdAt; // 更新日時 (Timestamp)
	

}
