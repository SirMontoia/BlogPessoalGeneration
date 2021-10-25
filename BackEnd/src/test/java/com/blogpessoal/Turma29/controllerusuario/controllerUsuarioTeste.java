package com.blogpessoal.Turma29.controllerusuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.Turma29.model.Usuarios;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
public class controllerUsuarioTeste {

	
	private @Autowired TestRestTemplate testrestTemplate;
	
	private Usuarios usuarioParaCriar;
	private Usuarios usuarioParaAlterar;
	
	@BeforeAll
	void start() {
		
			usuarioParaCriar = new Usuarios(0L, "Julio3", "julio3@email.com", "222222222");
			usuarioParaAlterar = new Usuarios (1L, "Julio ALTERADO", "julio@email.com", "1234567");
	}
	
	@Disabled
	@Test
	void salvarUsuarioNovoNoBancoRetornaStatus201() {
		
		HttpEntity<Usuarios> requisicao = new HttpEntity<Usuarios> (usuarioParaCriar);
		
		ResponseEntity<Usuarios> resposta = testrestTemplate.exchange("/api/v1/usuario/salvar", HttpMethod.POST,
				requisicao, Usuarios.class);
		
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	
	@Disabled
	@Test
	void buscarTodesRetornaStatus200() {
		
			ResponseEntity<String> resposta = testrestTemplate.withBasicAuth("julio3@email.com"	, "222222222")
					.exchange("/api/v1/usuario/todes", HttpMethod.GET, null, String.class);
			
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void atualizarUsuarioNoBancoRetornaStatus201() {
		
			HttpEntity<Usuarios> requisicao = new HttpEntity<Usuarios>(usuarioParaAlterar);
			
			ResponseEntity<Usuarios> resposta = testrestTemplate.withBasicAuth("julio3@email.com", "222222222")
					.exchange("/api/v1/usuario/atualizar,", HttpMethod.PUT, requisicao, Usuarios.class);
			
			
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	
}
