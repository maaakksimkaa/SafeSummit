package com.ssummit.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "user_seq", allocationSize = 1)
public class User extends GenericModel {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email",unique = true, nullable = false)
	private String email;

	@Column(name = "address", columnDefinition="TEXT")
	private String address;

	@Column(name = "passport_no")
	private String passportNo;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "role_id",
			foreignKey = @ForeignKey(name = "FK_USER_ROLE")
	)
	private Role role;

	@Column(name = "change_password_token")
	private String changePasswordToken;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "tour_participants",
            joinColumns = @JoinColumn(name = "user_id"),
            foreignKey =  @ForeignKey(name = "FK_USERS_TOURS"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"),
            inverseForeignKey = @ForeignKey(name = "FK_TOURS_USERS")
    )
    private Set<Tour> assignedTours = new HashSet<>();

	@Builder
	public User(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy,
	            boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy, String firstName, String middleName,
	            String lastName, LocalDate birthDate, String phone, String email, String address, String passportNo, String login,
	            String password, Role role) {
		super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.passportNo = passportNo;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String toStringFIO() {
		return role.getTitle() +
				": " + firstName + '\'' +
				", " + middleName + '\'' +
				", " + lastName + '\'';
	}
}
