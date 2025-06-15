package com.springboot.loginapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthResponse {
    public AuthResponse(String token2) {
		// TODO Auto-generated constructor stub
	}

	private String token;
}
