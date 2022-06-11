package com.sansfil.app.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="personnes")
public class Personne {

	@Id
	private String rfid;
	
	private String nom;
	
	private String prenom;
	
	private Blob image;
	
	private String email;
	
}
