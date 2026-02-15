package com.example.keepMates;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.keepMates.post.PostControllerImpl;
import com.example.keepMates.post.PostListDTO;
import com.example.keepMates.post.PostRequestDTO;
import com.example.keepMates.post.PostResponseDTO;
import com.example.keepMates.post.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PostControllerImpl.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostService postService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void GET_posts_年齢指定なし() throws Exception {
		PostListDTO p1 = new PostListDTO();
		p1.setId(1);
		p1.setUserId(123);
		p1.setImageUrl("url1");
		p1.setCaption("caption1");
		p1.setAge("20");

		PostListDTO p2 = new PostListDTO();
		p2.setId(2);
		p2.setUserId(456);
		p2.setImageUrl("url2");
		p2.setCaption("caption2");
		p2.setAge("30");
		
		// モックのサービスが呼ばれたときに返すレスポンスを設定
		PostResponseDTO resp = new PostResponseDTO();
		resp.setResultCode("00");
		resp.setPostList(Arrays.asList(p1, p2));
		
		// サービスのモックが呼ばれたときに、上で作ったレスポンスを返すように設定
		when(postService.getPosts(any(PostRequestDTO.class))).thenReturn(resp);
		
		// モックMVCを使って、コントローラーのGET /postsにリクエストを送る
		mockMvc.perform(get("/posts"))
				// レスポンスのステータスコードが200 OKであることを確認
				.andExpect(status().isOk())
				// レスポンスのJSONのresultCodeが"00"であることを確認
				.andExpect(jsonPath("$.resultCode").value("00"))
				// レスポンスのJSONのpostListの0番目のimageUrlが"url1"であることを確認
				.andExpect(jsonPath("$.postList[0].imageUrl").value("url1"))
				// レスポンスのJSONのpostListの1番目のimageUrlが"url2"であることを確認
				.andExpect(jsonPath("$.postList[1].imageUrl").value("url2"));
	}

	@Test
	void GET_posts_年齢指定あり() throws Exception {
		PostListDTO p1 = new PostListDTO();
		p1.setId(1);
		p1.setUserId(123);
		p1.setImageUrl("url1");
		p1.setCaption("caption1");
		p1.setAge("20");

		PostResponseDTO resp = new PostResponseDTO();
		resp.setResultCode("00");
		resp.setPostList(Arrays.asList(p1));

		when(postService.getPosts(any(PostRequestDTO.class))).thenReturn(resp);

		mockMvc.perform(get("/posts"))
		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value("00"))
				.andExpect(jsonPath("$.postList[0].age").value("20"));
	}
	
	@Test
	void GET_posts_異常系_500() throws Exception {

	    // サービスが呼ばれたら例外を投げるように設定
	    when(postService.getPosts(any(PostRequestDTO.class)))
	            .thenThrow(new RuntimeException("DBエラー"));

	    mockMvc.perform(get("/posts"))
	            .andExpect(status().isInternalServerError());
	}

	@Test
	void POST_posts_登録() throws Exception {
		PostResponseDTO resp = new PostResponseDTO();
		resp.setResultCode("00");

		when(postService.create(any(PostRequestDTO.class))).thenReturn(resp);

		PostRequestDTO req = new PostRequestDTO();
		req.setUserId(999);
		req.setImageUrl("newUrl");
		req.setCaption("newCaption");
		req.setAge("25");

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(req)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value("00"));
	}
	@Test
	void POST_posts_異常系_500() throws Exception {

	    // サービスが呼ばれたら例外を投げるように設定
	    when(postService.create(any(PostRequestDTO.class)))
	            .thenThrow(new RuntimeException("DBエラー"));

	    mockMvc.perform(post("/posts"))
	            .andExpect(status().isInternalServerError());
	}

}
