package com.example.keepMates.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
	
/*
 * ログイン機能:リクエストDTO
 */

// @Dataアノテーションは、Lombokライブラリを使用して、ゲッター、セッター、toString、equals、hashCodeなどのメソッドを自動生成します。
@Data
public class LoginRequestDTO {

	/** ユーザーが入力したID */
	@JsonProperty("userId")
    private Integer userId;
    
    /** ユーザーが入力したパスワード */
	@JsonProperty("password")
    private String password;

}
