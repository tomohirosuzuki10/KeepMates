package com.example.keepMates.login;

import lombok.Data;
	
/*
 * ログイン機能:リクエストDTO
 */

// @Dataアノテーションは、Lombokライブラリを使用して、ゲッター、セッター、toString、equals、hashCodeなどのメソッドを自動生成します。
@Data
public class LoginRequestDTO {

	/** ユーザーが入力したID */
    private Integer userId;
    
    /** ユーザーが入力したパスワード */
    private String password;

}
