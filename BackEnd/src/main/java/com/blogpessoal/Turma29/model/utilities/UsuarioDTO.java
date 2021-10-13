package com.blogpessoal.Turma29.model.utilities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/*
 * Classe responsavel por validar aceso de usuario no sistema. É necessario que 
 * seja passado pelo usuario o email e a senha para autenticação
 * 
 * @author Turma 29
 * 
 * 
 * @since 1.0
 * 
 */


public class UsuarioDTO {
	
	private @NotBlank @Email String email;
	private @NotBlank String senha;
	
	private	Long idUsuario;
	private String nome;
	private String token;
	private String foto;
	private String tipo;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
