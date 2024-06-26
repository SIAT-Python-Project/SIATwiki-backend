package com.webserver.siatwiki.user.api;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.webserver.siatwiki.user.dto.UserDTO;
import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.dto.UserDTO.UserResponseDTO;
import com.webserver.siatwiki.user.dto.UserLoginDTO.UserLoginResponseDTO;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class UserController {
	
    private final UserService userService;
    
    // 유저 회원가입
    @PostMapping("/api/sign-up")
    public ResponseEntity<User> createUser(@RequestBody UserDTO.UserRequestDTO requestDTO) {
        HttpStatus status = HttpStatus.CREATED;
        User user = userService.createUser(requestDTO);		
        return new ResponseEntity<>(user, status);
    }

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<Boolean> findUserLogin(@RequestBody UserDTO.UserRequestDTO requestDTO, HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpStatus status = HttpStatus.OK;
        boolean loginSuccess = userService.findUserLogin(requestDTO.getEmail(), requestDTO.getPassword());
        User cookieUser = userService.getByEmail(requestDTO.getEmail());
        UserLoginResponseDTO userLoginDTO = new UserLoginResponseDTO(cookieUser);    
        request.setAttribute("userLoginDTO", userLoginDTO);	// 인터셉터에 userLoginDTO 사용
        
        return new ResponseEntity<>(status);

    }

    // 로그아웃
    @PostMapping("/api/logout")
    public ResponseEntity<Boolean> findUserLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    // UserList
    @PostMapping("/api/userFind")
    public ResponseEntity<List<UserResponseDTO>> findUser(UserRequestDTO userRequsetDTO) {
        HttpStatus status = HttpStatus.OK;
        List<UserDTO.UserResponseDTO> responseDTO = userService.findUser();
        return new ResponseEntity<>(responseDTO, status);
    }

}
