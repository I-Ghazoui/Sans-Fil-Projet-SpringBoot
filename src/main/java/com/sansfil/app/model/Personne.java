package com.sansfil.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="personnes")
public class Personne {

	@Id
	private String rfid;
	
	private String nom;
	
	private String prenom;
	
	private String poste;
	
	private String image;
	
	private String email;
	
	@CreationTimestamp
	@Column(name="date_creation")
	private LocalDateTime dateCreation;	
}
