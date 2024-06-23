package com.webserver.siatwiki.user.dto;

import com.webserver.siatwiki.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserLoginDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserLoginResponseDTO {
		private String id;
		private String name;
		private String email;

		public UserLoginResponseDTO(User user) {
	        this.id = String.valueOf(user.getId());
			this.name = user.getName();
			this.email = user.getEmail();

		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserLoginRequestDTO {
		private String id;
		private String name;
		private String email;
	    private String password;

	}
}
