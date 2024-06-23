package com.webserver.siatwiki.user.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.webserver.siatwiki.person.entity.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;             // user 번호
   
   @Column(name = "name")
   private String name;         // user 이름

   @Column(name = "email")
    private String email;         // user email

   @Column(name = "password")
    private String password;      // user password
   
   @Column(name = "role")
    private Role role;         // USER와 ADMIN

    @Column(name = "create_date")
    private LocalDateTime  createDate;   // user 생성일
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Person> persons;

    @Builder
   public User(String name, String email, String password, Role role, LocalDateTime createDate) {
      this.name = name;
      this.email = email;
      this.password = password;
      this.role = role;
      this.createDate = createDate;
   }
    
    
}