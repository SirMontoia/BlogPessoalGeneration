package com.blogpessoal.Turma29.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tema {

	
	private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long idTema;
	private @NotBlank String tema;
	
	//ABAIXO É FEITO O RELACIONAMENTO ENTRE AS ENTIDADES TEMA E POSTAGEM
	//JSON IGNORE PROPERTIES IMPEDE O LOOP QUE OCORRE COM MINHAS POSTAGENS E CRIADOR
	// BASICAMENTE, ENTRA EM MINHAS POSTAGENS, TEM POSTAGENS E DENTRO DE POSTAGENS TEM UM CRIADOR, QUE TAMBÉM TEM MINHAS POSTAGENS, FORMANDO O LOOP
	// JSON IGNORE ENTRA PARA IGNORAR O CRIADOR, IMPEDINDO A FORMAÇÃO DO LOOP
	
	@OneToMany(mappedBy = "temaRelacionado", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"temaRelacionado"})
	private List<Postagem> postagens = new ArrayList<>();

	public Long getIdTema() {
		return idTema;}
	
	public void setIdTema(Long idTema) {
		this.idTema = idTema;}
	
	public String getTema() {
		return tema;}
	
	public void setTema(String tema) {
		this.tema = tema;}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	





}

