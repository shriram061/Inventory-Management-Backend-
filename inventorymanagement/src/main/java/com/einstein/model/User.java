package com.einstein.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * User Table
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@ApiModel(value = "Model class for User.")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@ApiModelProperty(value = "userId of the User.")
	private int userId;

	@ApiModelProperty(value = "userName of the User.")
	@Column(unique = true)
	private String userName;
	@ApiModelProperty(value = "emailId of the User.")
	@Column(unique = true)
	private String emailId;
	@ApiModelProperty(value = "password of the User.")
	private String password;
	
}