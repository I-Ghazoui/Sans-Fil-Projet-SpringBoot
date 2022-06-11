package com.sansfil.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Data
@Table(name="personne_salle")
public class PersonneSalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pers", referencedColumnName = "rfid")
	private Personne personne;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_classe", referencedColumnName = "id")
	private Salle salle;
	
	@CreatedDate
	private Date date_entree;

}
