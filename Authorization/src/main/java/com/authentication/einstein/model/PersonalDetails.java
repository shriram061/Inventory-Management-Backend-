package com.authentication.einstein.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//store of details
@Data
@Entity
@Table(name = "user_details")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {
	@Column(unique = true)
	@Id
	private String userName;
	private String password;
	private String emailId;
	
}
