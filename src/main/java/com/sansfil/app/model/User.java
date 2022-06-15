package com.sansfil.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Admins")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="prenom")
	private String firstName;
	
	@Column(name="nom")
	private String lastName;
	
	private String image;
	
	@Column(unique=true)
	private String username;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poste_id")
	private Poste poste;
	
	@Column(name = "date_creation_compte")
	private Date dateCreationCompte;
	*/
}
