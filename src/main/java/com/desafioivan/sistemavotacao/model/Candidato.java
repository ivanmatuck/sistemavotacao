package com.desafioivan.sistemavotacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="candidato")
public class Candidato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable=false, unique=true, nullable=false)
	private Long id;
	
	@Column(name="Numero_Candidato", nullable=false)
	private Long numeroCandidato;
	
	@Column(name="Nome_Candidato", nullable=false)
	private String nomeCandidato;
	
	
	@Column(name="Qtde_Votos", nullable=false)
	private Integer numeroDeVotos;



	
}
