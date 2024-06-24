package com.webserver.siatwiki.user.dto;

import java.time.LocalDateTime;

import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserResponseDTO {
		private Long id;
		private String name;
		private String email;
		private String password;
		private Role role;
		private LocalDateTime createDate;

		public UserResponseDTO(User user) {
			this.id = user.getId();
			this.name = user.getName();
			this.email = user.getEmail();
			this.password = user.getPassword();
			this.role = user.getRole();
			this.createDate = user.getCreateDate();
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserRequestDTO {
		private Long id;
		private String name;
		private String email;
		private String password;
		private Role role;
		private LocalDateTime createDate;
	}

}
