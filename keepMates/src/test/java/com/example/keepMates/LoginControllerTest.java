package com.example.keepMates;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.keepMates.login.LoginControllerImpl;
import com.example.keepMates.login.LoginRequestDTO;
import com.example.keepMates.login.LoginResponseDTO;
import com.example.keepMates.login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoginControllerImpl.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void POST_login_success() throws Exception {
        LoginResponseDTO resp = new LoginResponseDTO();
        resp.setResultCode("00");
        resp.setUserName("DB登録");

        when(loginService.login(any(LoginRequestDTO.class))).thenReturn(resp);

        LoginRequestDTO req = new LoginRequestDTO();
        req.setUserId(123);
        req.setPassword("dbpass");

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultCode").value("00"))
            .andExpect(jsonPath("$.userName").value("DB登録"));
    }

    @Test
    void POST_login_failure() throws Exception {
        LoginResponseDTO resp = new LoginResponseDTO();
        resp.setResultCode("01");

        when(loginService.login(any(LoginRequestDTO.class))).thenReturn(resp);

        LoginRequestDTO req = new LoginRequestDTO();
        req.setUserId(123);
        req.setPassword("wrong");

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.resultCode").value("01"));
    }

}
