package com.blogpessoal.Turma29.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.model.Usuarios;
import com.blogpessoal.Turma29.repository.PostagemRepositorio;
// CONTROLADOR TEM A FUNÇÃO DE COMUNICAÇÃO COM O CLIENT
// AQUI SE DEFINE O CONTROLADOR, RESPONSÁVEL PELO ACESSO A PAGINA E SE DEFINE O ENDPOINT DA APLICAÇO
// NO CASO, O MÉTODO PEGAR TODES NOS MOSTRA A LISTA CRIADA EM CIMA DE POSTAGENS
// REST CONTROLLER INDICA QUE A CLASSE SE TRATA DE UMA CLASSE CONTROLE.
// REST MAPPING DEFINE POR QUAL A URL QUE ESSA CLASSE SERÁ ACESSADA
// QUANDO HOUVER REQUISIÇÃO AO SERVIDOR USANDO API/V1/POSTAGEM, A REQUISIÇÃO PASSARÁ A CONSUMIR ESSA CLASSE

@RestController
@RequestMapping("/api/v1/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemControlador {

	// PRIMEIRO SE INJETA A NOSSA CLASSE REPOSITORIO DENTRO DO CONTROLLER
	// AUTOWIRED PERMITE QUE A INTERFACE SEJA INSTANCIADA AQUI. PERMITE A INSERÇÃO E
	// UTILIZAÇÃO DOS SERVIÇOS DA INTERFACE NO CONTROLLER
	// AUTOWIRED É O SERVIÇO DE GESTÃO DE DEPENDÊNCIAS DO SPRING

	private @Autowired PostagemRepositorio repositorio;

	/*
	 * PRIMEIRO MÉTODO DE FIND ALL: PUBLICO, DO TIPO RESPONSE ENTITY QUE RETORNA UMA
	 * LISTA DO TIPO POSTAGEM. NOME DO METODO É GETALL SEM NADA COMO PARAMETRO. NO
	 * RETORNO, TEMOS UMA RESPONSEENTITY COM UMA RESPOSTA HHTP 'OK' E DENTRO DO
	 * BODY, EXISTE A REQUISIÇÃO DE TODOS AS POSTAGENS. GETMAPPING ENTRA PARA
	 * INFORMAR QUE SE TRATA DE UM GET DO SERVIDOR E QUE SE A REQUISIÇÃO DO USUARIO
	 * FOR DO TIPO GET, O PROGRAMA CONSOME O MÉTODO GETALL
	 */
	// GET É O VERBO HHTP

	@GetMapping("/todas")

	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repositorio.findAll());
	}

	// AGORA SE DETERMINA UM END POINT QUE SALVA UMA NOVA POSTAGEM DENTRO DO BANCO
	// DE DADOS
	// PUBLICO, COM NOME SALVAR, RECEBE COMO PARAMETRO A CLASSE USUARIO COM ATRIBUTO
	// NOVO USUARIO E RETORNA RESPONSE ENTITY DE USUARIO
	// AQUI SE TEM UM METODO QUE SALVA UM PARAMETRO ENVIADO DO BODY DA REQUISIÇÃO
	// REQUEST BODY É UMA ANOTAÇÃO PARA PEGAR ESSE PARAMETRO
	// REQUEST BODY: AQUILO QUE O USUARIO MANDAR PELO BODY VAI SER PEGO DENTRO DA
	// VARIAVEL NOVAPOSTAGEM E SERA USADO DENTRO DO METODO
	// VALID É USADO COM REQUEST BODY QUE REQUISITA ALGO DO FRONTEND
	// VALID VALIDA AQUILO O QUE O FRONTEND ENVIA 

	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvar(@Valid @RequestBody Postagem novaPostagem) {

		return ResponseEntity.status(201).body(repositorio.save(novaPostagem));
	}
	
	@GetMapping ("/{id_postagem}")
	
	public ResponseEntity <Postagem> buscarPorId (@PathVariable (value = "id_postagem") Long idPostagem){ 
		Optional <Postagem> objetoUsuarios = repositorio.findById(idPostagem);
		
		if(objetoUsuarios.isPresent()) 
		{
			return ResponseEntity.status(200).body (objetoUsuarios.get());
		}
		else 
		{
			return ResponseEntity.status(204).build();
		}
			
}
	
	@PutMapping ("/atualizar")
	public ResponseEntity<Postagem> atualizar (@Valid @RequestBody Postagem postagemParaAtualizar){
	
		return ResponseEntity.status(201).body(repositorio.save(postagemParaAtualizar));
}
	
	@DeleteMapping ("/deletar/{id_postagem}")
	public void deletarTemaPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		repositorio.deleteById(idPostagem);
	}
	
	 @GetMapping("/pesquisa/{titulo}")
	    public ResponseEntity<List<Postagem>> buscarPorTituloII(@PathVariable (value = "titulo") String titulo) {
	        List<Postagem> objetoLista = repositorio.findAllByTituloContainingIgnoreCase(titulo);

	        if (objetoLista.isEmpty()) {
	            return ResponseEntity.status(204).build();
	        } else {
	            return ResponseEntity.status(200).body(objetoLista);
	        }
	    }

	
	
	
	

}
