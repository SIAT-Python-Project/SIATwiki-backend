package com.webserver.siatwiki.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserQueryDSLRepository;
import com.webserver.siatwiki.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import static com.webserver.siatwiki.common.response.error.ErrorCode.DUPLICATE_USER_EMAIL;
import static com.webserver.siatwiki.common.response.error.ErrorCode.LOGIN_FAIL;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final UserQueryDSLRepository userQueryDSLRepository;

	@Transactional
	public User createUser(UserRequestDTO requestDTO) {
		User duplicateUser = userQueryDSLRepository.findByEmail(requestDTO.getEmail());	// 이메일 중복 확인

		if (duplicateUser != null) {
			throw new CustomException(DUPLICATE_USER_EMAIL);
		}

		User user = DtoToEntity(requestDTO);
		User newUser = userRepository.save(user);	// USER 회원 생성
		return newUser;
	}

	@Transactional
	public boolean findUserLogin(String email, String password) {
		User user = userQueryDSLRepository.findByEmail(email);	// 로그인을 하기위해 이메일 중복 확인

		if (user == null || !user.getPassword().equals(password)) {
			throw new CustomException(LOGIN_FAIL);
		}
		return true;
	}

	@Transactional
	public List<UserDTO.UserResponseDTO> findUser() {
		return userRepository		// USER에 등록된 회원 전부 가져오기
				.findAll().stream()
				.map(user -> new UserDTO.UserResponseDTO(user.getId(), 
						user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getCreateDate()))
				.collect(Collectors.toList());
	}

	@Transactional
	public boolean findUserLogout(String email) {
		User user = userQueryDSLRepository.findByEmail(email);	// 이메일 확인 후 로그아웃

		if (user == null) {
			throw new CustomException(LOGIN_FAIL);
		}

		return true;
	}

	@Transactional
	public User getByEmail(String email) {
		return userQueryDSLRepository.findIdAndNameAndEmailByEmail(email);	// 이메일과 이름 아이디를 가져오기
	}

	public User DtoToEntity(UserRequestDTO requestDTO) {
		User user = User.builder()			// USER 생성시 Role을 USER로 고정
				.name(requestDTO.getName())
				.email(requestDTO.getEmail())
				.password(requestDTO.getPassword())
				.role(Role.USER)
				.createDate(LocalDateTime.now()).build();

		return user;
	}
}