package com.webserver.siatwiki.user.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.dto.UserDTO.UserResponseDTO;
import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;

	@PostMapping("/api/test")
	public void usertest() {
		User user = User.builder() 
						.name("chang") 
						.email("bb@asd") 
						.password("123")
						.role(Role.ADMIN)
						.createDate(LocalDateTime.now())
						.build();
		
		userService.userTest(user);	
	}
	
//	@PostMapping("/api/sign-up")
//	public void createUser() {
//		User user = User.builder() 
//						.name("chang") 
//						.email("bb@asd") 
//						.password("123")
//						.role(Role.ADMIN)
//						.createDate(LocalDateTime.now())
//						.build();
//		
//		userService.userSignup(user);	
//	}
	
	@PostMapping("/api/login")
	public ResponseEntity<?> findUserLogin(@RequestBody UserRequestDTO loginUserRequestDTO) throws NoSuchMethodException {
		boolean loginSuccess = userService.findUserLogin(loginUserRequestDTO.getEmail(), loginUserRequestDTO.getPassword(), loginUserRequestDTO);
		System.out.println(loginSuccess);
		if(loginSuccess) {
			return ResponseEntity.ok(true);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}
	
	@PostMapping("/api/userFind")
	public ResponseEntity<List<UserResponseDTO>> findUser(UserRequestDTO userRequsetDTO){
		List<User> result  = userService.findUser();
	    List<UserResponseDTO> responseDTO = result.stream()
	            .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getCreateDate()))
	            .collect(Collectors.toList());
	    return ResponseEntity.ok(responseDTO);
	}
	
	
	@PostMapping("/api/logout")
	public User findUserLogout(@RequestBody UserRequestDTO logoutUserRequestDTO) {
		User logResult = userService.findUserLogout(logoutUserRequestDTO.getEmail());
		System.out.println(logResult);
		return logResult;
	}


}
