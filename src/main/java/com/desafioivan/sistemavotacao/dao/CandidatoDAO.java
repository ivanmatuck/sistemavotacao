package com.desafioivan.sistemavotacao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafioivan.sistemavotacao.model.Candidato;

@Repository
public interface CandidatoDAO extends JpaRepository<Candidato, Integer>{

	Candidato findById(Long id);

	Candidato findByNumeroCandidato(Long numeroCandidato);


}
