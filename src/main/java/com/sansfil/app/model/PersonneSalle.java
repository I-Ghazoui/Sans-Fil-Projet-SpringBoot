package com.sansfil.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="personne_salle")
public class PersonneSalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "id_pers", referencedColumnName = "rfid")
	private Personne personne;
	
	@OneToOne
    @JoinColumn(name = "id_classe", referencedColumnName = "id")
	private Salle salle;
	
	@CreationTimestamp
	@Column(name="date_entree")
	private LocalDateTime dateEntree;

}
