package com.einstein.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int contactId;
private String name;
private long phoneNo;
private String emailId;
@Column(length = 1000)
@Type(type = "org.hibernate.type.TextType")
private String question;
}
