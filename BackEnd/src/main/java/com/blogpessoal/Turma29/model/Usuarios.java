package com.blogpessoal.Turma29.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuarios {

	// DECLARAÇÃO DE MAIS UMA TABELA, AGORA COM EMAIL (IMPORTAR A BIBLIOTECA) E UMA SENHA (IMPORTAR SIZE)
	// SE DETERMINOU O TAMANHO MÍNIMO DA SENHA
	
	private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long idUsuario;
	private @NotBlank String nome;
	private @NotBlank @Email String email;
	private @NotBlank @Size (min=5) String senha;
	private String foto;
	private String tipo;
	
	
	
	//ABAIXO SEGUE A UNIÃO ENTRE TABELAS (RELACIONAR ENTIDADES)
	//UNIR USUARIOS COM POSTAGEM
	// ONE TO MANY POIS UM USUARIO PODE CRIAR MÚLTIPLAS POSTAGENS
	// ELE É MAPEADO PELA CLASSE POSTAGEM (PAI DO RELACIONAMENTO)
	// CASCADE EXISTE PARA QUE A DELEÇÃO DE UMA POSTAGEM NÃO DELETE O USUARIO JUNTO
	
	public Usuarios (@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size (min=5) String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha= senha;
	}
	
	public Usuarios (Long idUsuario, String nome, String email, String senha) {
		
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public Usuarios () {};
	
	@OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"criador"})
	private List<Postagem> minhasPostagens = new ArrayList<>();

	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Postagem> getMinhasPostagens() {
		return minhasPostagens;
	}

	public void setMinhasPostagens(List<Postagem> minhasPostagens) {
		this.minhasPostagens = minhasPostagens;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
