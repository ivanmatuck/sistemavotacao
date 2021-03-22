package com.desafioivan.sistemavotacao.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafioivan.sistemavotacao.dao.AssociadoDAO;
import com.desafioivan.sistemavotacao.dao.CandidatoDAO;
import com.desafioivan.sistemavotacao.model.Associado;
import com.desafioivan.sistemavotacao.model.Candidato;

@Controller
public class VotacaoController {

	public final Logger logger = Logger.getLogger(VotacaoController.class);

	@Autowired
	AssociadoDAO associadoDAO;

	@Autowired
	CandidatoDAO candidatoDAO;

	@RequestMapping("/")
	public String iniciarVotacao() {
		return "votacao.html";
	}

	@RequestMapping("/fazerlogin")
	public String fazerLogin(@RequestParam String nome, Model model, HttpSession session) {
		logger.debug("Buscando associado na base de dados");
		Associado associado = associadoDAO.findByNome(nome);

		logger.info("Enviando candidatos à sessão");
		session.setAttribute("associado", associado);

		if(!associado.getVotado()){
			logger.info("Enviando candidatos ao model");
			List<Candidato> candidatos = candidatoDAO.findAll();
			model.addAttribute("candidatos", candidatos);
			return "/votar.html";
		} else{
			return "/votouanteriormente.html";
		}
	}


	@RequestMapping("/votoPara")
	public String votoPara(@RequestParam Long numeroCandidato, HttpSession session) {
		Associado associado = (Associado) session.getAttribute("associado");
		if(!associado.getVotado()) {
			associado.setVotado(true);

			Candidato candidato = candidatoDAO.findByNumeroCandidato((Long)numeroCandidato);
			logger.info("Voto para o candidato "+ candidato.getNomeCandidato());
			candidato.setNumeroDeVotos(candidato.getNumeroDeVotos() + 1);

			candidatoDAO.save(candidato);
			associadoDAO.save(associado);

			return "votou.html";
		} else {
			return "votouanteriormente.html";
		}
	}



}
