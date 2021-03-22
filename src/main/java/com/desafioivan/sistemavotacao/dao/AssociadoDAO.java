package com.desafioivan.sistemavotacao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafioivan.sistemavotacao.model.Associado;


@Repository
public interface AssociadoDAO extends JpaRepository<Associado, Long> {

	Associado findByNome(String nome);
}
