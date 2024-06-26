package com.webserver.siatwiki.person.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webserver.siatwiki.info.entity.Info;
import com.webserver.siatwiki.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.webserver.siatwiki.user.entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"infos", "user"})
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 			// person 번호

	@Column(name = "name", length = 200)
	private String name;		// person 이름

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;	// person 생성일

	@UpdateTimestamp
	@Column(name = "update_date")
	private LocalDateTime updateDate;	// person 수정일

	@Column(name = "mbti", length = 50)
	private String mbti;			// person mbti

	@Column(name = "email", length = 200)
	private String email;			// person email

	@Column(name = "github", length = 1000)
	private String github;			// person github 메일

	@JoinColumn(name="user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	private User user;			// User

	@OneToMany(mappedBy = "person", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Info> infos;

	@OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Builder
	public Person(String name, LocalDateTime createDate, LocalDateTime updateDate, String mbti, String email,
			String github, User user, Profile profile) {
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.mbti = mbti;
		this.email = email;
		this.github = github;
		this.user = user;
		this.profile = profile;
	}


	public void update(String name, String mbti, String email, String github, User user){
		this.name = name;
		this.mbti = mbti;
		this.email = email;
		this.github = github;
		this.user = user; //마지막 수정자
		this.updateDate = LocalDateTime.now(); // 업데이트 시점 갱신
	}


}
