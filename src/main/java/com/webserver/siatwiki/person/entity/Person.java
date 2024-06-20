package com.webserver.siatwiki.person.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.webserver.siatwiki.info.entity.Info;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.webserver.siatwiki.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; 			// person 번호

	@Column(name = "name")
	private String name;		// person 이름

	@Column(name = "create_date")
	private LocalDateTime createDate;	// person 생성일

	@Column(name = "update_date")
	private LocalDateTime updateDate;	// person 수정일
	
	@Column(name = "mbti")
	private String mbti;			// person mbti
	
	@Column(name = "email")
	private String email;			// person email
	
	@Column(name = "github")
	private String github;			// person github 메일			
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;			// User
	
	@OneToMany(mappedBy = "person")
	private List<Info> infos;
	
	@Builder
	public Person(String name, LocalDateTime createDate, LocalDateTime updateDate, String mbti, String email,
			String github, User user) {
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.mbti = mbti;
		this.email = email;
		this.github = github;
		this.user = user;
	}


}
