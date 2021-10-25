package com.blogpessoal.Turma29.modelusuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import com.blogpessoal.Turma29.model.Usuarios;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class modelUsuarioTeste {

	public Usuarios usuario;

	private @Autowired ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	
	@BeforeEach
	public void start() {
		usuario = new Usuarios ("Julio", "julio@email.com", "1234567");
	}

	@Test
	void validaUsuarioCorretoRetornaTrue() {
		Set<ConstraintViolation<Usuarios>> objetoViolacao = validator.validate(usuario);
		assertTrue(objetoViolacao.isEmpty());
	
	}
	
	@Test
	void validaNomeDeUsuarioIncorretoRetornaFalse() {
		
		Usuarios usuarioComFalha = new Usuarios(null, "alguem@email", "senha12345");
		Set<ConstraintViolation<Usuarios>> objetoViolacao = validator.validate(usuarioComFalha);
		assertFalse(objetoViolacao.isEmpty());
	}
}
