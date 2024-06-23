package com.webserver.siatwiki.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.user.dto.UserDTO;
import com.webserver.siatwiki.user.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.webserver.siatwiki.user.dto.UserDTO.UserRequestDTO;
import com.webserver.siatwiki.user.dto.UserLoginDTO.UserLoginResponseDTO;
import com.webserver.siatwiki.user.entity.Role;
import com.webserver.siatwiki.user.entity.User;
import com.webserver.siatwiki.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import static com.webserver.siatwiki.common.response.error.ErrorCode.DUPLICATE_USER_EMAIL;
import static com.webserver.siatwiki.common.response.error.ErrorCode.LOGIN_FAIL;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
	private final UserRepository userRepository;
//	@Transactional
//	public void userTest(User user) {
//		userRepository.save(user);
//	}

	@Transactional
	public User createUser(UserRequestDTO requestDTO) {
		User duplicateUser = userRepository.findByEmail(requestDTO.getEmail());

		if (duplicateUser != null) {
			throw new CustomException(DUPLICATE_USER_EMAIL);
		}

		User user = DtoToEntity(requestDTO);
		User newUser =userRepository.save(user);
		return newUser;
	}

	@Transactional
	public boolean findUserLogin(String email, String password) {
		User user = userRepository.findByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			throw new CustomException(LOGIN_FAIL);
		}
		return true;
	}

	@Transactional
	public List<UserDTO.UserResponseDTO> findUser() {
		return userRepository.findAll().stream()
				.map(user -> new UserDTO.UserResponseDTO(user.getId(),
						user.getName(),
						user.getEmail(),
						user.getPassword(),
						user.getRole(),
						user.getCreateDate()))
				.collect(Collectors.toList());
	}

	@Transactional
	public boolean findUserLogout(String email) {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new CustomException(LOGIN_FAIL);
		}

		return true;
	}

	@Transactional
	public User getByEmail(String email) {
		return userRepository.findIdAndNameAndEmailByEmail(email);
	}

	public User DtoToEntity(UserRequestDTO requestDTO) {
		User user = User.builder()
				.name(requestDTO.getName())
				.email(requestDTO.getEmail())
				.password(requestDTO.getPassword())
				.role(Role.USER)
				.createDate(LocalDateTime.now())
				.build();

		return user;

	}


}
