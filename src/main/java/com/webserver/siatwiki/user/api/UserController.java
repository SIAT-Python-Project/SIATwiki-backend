package com.webserver.siatwiki.user.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webserver.siatwiki.user.dto.UserDTO;
import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.dto.UserDTO.UserResponseDTO;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	@PostMapping("/api/sign-up")
	public ResponseEntity createUser(@RequestBody UserDTO.UserRequestDTO requestDTO) {
		User user = null;
		try {
			// 기존 사용자와 요청된 이메일 비교
			List<User> existingUser = userService.findUser();
			for (User checkUser : existingUser) {
				if (checkUser != null && checkUser.getEmail().equals(requestDTO.getEmail())) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("이메일 존재합니다");
				}
			}
			user = userService.DtoToEntity(requestDTO);
			userService.createUser(user);
		} catch (Exception e) {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PostMapping("/api/login")
	public ResponseEntity findUserLogin(@RequestBody UserDTO.UserRequestDTO requestDTO) {
		boolean loginSuccess = userService.findUserLogin(requestDTO.getEmail(), requestDTO.getPassword());
		if (loginSuccess) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email 또는 password가 다름니다");
		}
	}

	@PostMapping("/api/logout")
	public ResponseEntity findUserLogout(@RequestBody UserDTO.UserRequestDTO requestDTO) {
		boolean logoutSuccess = userService.findUserLogout(requestDTO.getEmail());
		if (logoutSuccess) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping("/api/userFind")
	public ResponseEntity<List<UserResponseDTO>> findUser(UserRequestDTO userRequsetDTO) {
		List<User> result = userService.findUser();
		if (result != null) {
			List<UserResponseDTO> responseDTO = result.stream()
					.map(user -> new UserResponseDTO(user.getId(),
					user.getName(), 
					user.getEmail(), 
					user.getPassword(), 
					user.getRole(),
					user.getCreateDate()))
					.collect(Collectors.toList());
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

	}

//	@PostMapping("/api/test")
//	public void usertest() {
//		User user = User.builder() 
//						.name("chang") 
//						.email("bb@asd") 
//						.password("123")
//						.role(Role.ADMIN)
//						.createDate(LocalDateTime.now())
//						.build();
//		
//		userService.userTest(user);	
//	}

}
