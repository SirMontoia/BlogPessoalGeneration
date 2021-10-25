package com.blogpessoal.Turma29.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma29.model.Usuarios;

@Repository
public interface UsuariosRepositorio extends JpaRepository <Usuarios, Long> {
		
	// AQUI SE DECLAROU UM MÉTODO QUE PODE SER CHAMADO COMO UMA DAS FORMAS DE FINDALLBY
	// ABAIXO, SEGUE PRIMEIRO SUA DOCUMENTAÇÃO DEPOIS O MÉTODO EM SI
	
	/*
	 * 
	 * Método utilizado para pesquisar coluna nome ContainingIgnoreCase
	 *
	 * 
	 * @param nome do tipo String
	 * @return List de  Usuarios
	 * @author Turma 29
	 */
	
	List <Usuarios> findAllByNomeContainingIgnoreCase (String nome);
	
	/*
	 * Método utilizado para pesquisar coluna email
	 * 
	 * 
	 * @param email do tipo string
	 * 
	 * @return Optional com Usuario 
	 *
	 *@author Turma 29
	 *@since 1.0
	 */
	
	
	
	Optional<Usuarios> findByEmail (String email);
	
	
	
}
