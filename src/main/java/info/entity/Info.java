package info.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import person.entity.Person;
import user.entity.User;

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
	private String type;			// 카테고리
	
	@Column(name = "content")
	private String content;			// 카테고리 작성

	@Column(name = "create_date")
	private LocalDateTime createDate;	// info 생성일

	@Column(name = "update_date")
	private LocalDateTime updateDate;	// info 수정일

	@Column(name = "person")
    @OneToMany(mappedBy = "info")
    private Person person;				// person

	@Builder
	public Info(String type, String content, LocalDateTime createDate, LocalDateTime updateDate, Person person) {
		this.type = type;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.person = person;
	}



}
