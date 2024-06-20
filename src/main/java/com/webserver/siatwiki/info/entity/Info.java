package com.webserver.siatwiki.info.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.webserver.siatwiki.person.entity.Person;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; 				// info 번호

	@Column(name = "type")
	private Category type;			// 카테고리
	
	@Column(name = "content")
	private String content;			// 카테고리 작성

	@Column(name = "create_date")
	@CreatedDate
	private LocalDateTime createDate;	// info 생성일

	@Column(name = "update_date")
	@LastModifiedDate
	private LocalDateTime updateDate;	// info 수정일

	@JoinColumn(name="user_id")
	@ManyToOne
    private Person person;				// person

	@Builder
	public Info(Category type, String content, LocalDateTime createDate, LocalDateTime updateDate, Person person) {
		this.type = type;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.person = person;
	}



}
