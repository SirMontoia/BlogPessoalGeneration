package com.blogpessoal.Turma29.repositoryusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.Turma29.model.Usuarios;
import com.blogpessoal.Turma29.repository.UsuariosRepositorio;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class repositorioUsuarioTeste {

	
	private @Autowired UsuariosRepositorio repositorio;
	
	@BeforeEach
	void start() {
		
			Usuarios objeto1 = new Usuarios(0L, "Julio 2", "julio2@email.com", "1111111");
			if(!repositorio.findByEmail(objeto1.getEmail()).isPresent())
				repositorio.save(objeto1);
			
			Usuarios objeto2= new Usuarios (0L, "Alguém 2", "alguem@email.com", "123333333");
			if(!repositorio.findByEmail(objeto2.getEmail()).isPresent())
				repositorio.save(objeto2);	
			
			
	}
	
	@Test
	void findByEmailExistenteRetornaTrue() {
		
		Usuarios objetoJulio = repositorio.findByEmail("julio2@email.com").get();
		
		assertTrue(objetoJulio.getEmail().equals("julio2@email.com"));
	}

	@Test
	void findAllByNomeContainingIgnoreCaseRetornaDoisUsuarios() {
		List<Usuarios> objetoLista = repositorio.findAllByNomeContainingIgnoreCase("2");
		
		assertEquals(2, objetoLista.size());
	}

	@AfterAll
	void end() {
		System.out.println("Teste finalizado!");
	}

}



