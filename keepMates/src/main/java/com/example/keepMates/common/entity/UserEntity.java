package com.example.keepMates.common.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
/**
 * ユーザー情報エンティティ
 */
@Entity //Spring Boot（JPA）にこのクラスがエンティティであることを示します。
@Table(name = "users") //データベースの"users"テーブルにマッピングされることを指定します。
@Data //Lombokの@Dataアノテーションを使用して、ゲッター、セッター、toString、equals、hashCodeなどのメソッドを自動生成します。
public class UserEntity {
	
	@Id //このフィールドがエンティティの主キーであることを示します。
    @Column(name = "id") 
    private Integer id; // ユーザーID (Integer / PK)

    @Column(name = "password")
    private String password; // パスワード (String)

    @Column(name = "username")
    private String userName; // 名前 (String)

    @Column(name = "birth_date")
    private LocalDate birthDate; // 生年月日 (Date)

    @Column(name = "created_at")
    private Timestamp profileImage; // アイコン画像 (String)

}
