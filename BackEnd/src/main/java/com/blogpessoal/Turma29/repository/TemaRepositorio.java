package com.blogpessoal.Turma29.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.model.Tema;

@Repository
public interface TemaRepositorio extends JpaRepository <Tema, Long> {
	
	public List<Tema> findByTemaContainingIgnoreCase (String tema);

}
