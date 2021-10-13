 package com.blogpessoal.Turma29.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.model.Tema;
import com.blogpessoal.Turma29.model.Usuarios;
import com.blogpessoal.Turma29.model.utilities.UsuarioDTO;
import com.blogpessoal.Turma29.repository.UsuariosRepositorio;
import com.blogpessoal.Turma29.servicos.UsuarioServicos;

@RestController
@RequestMapping ("/api/v1/usuario")
@CrossOrigin("*")

public class UsuariosControladores {
	
	private @Autowired UsuariosRepositorio repositorio;
	private @Autowired UsuarioServicos servicos;
	
	@GetMapping ("/todes")
	public ResponseEntity <List<Usuarios>> pegarTodes(){
	
		List<Usuarios> objetoLista = repositorio.findAll();
		
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	//AGORA SE DEFINE O MÉTODO PARA PEGAR DADOS SINGULARES. 
		// É PUBLICO, RESPONSEENTITY DE UM USUARIO.
		// RECEBE UM LONG COM IDUSUARIO
		// RETORNO É UM RESPONSEENTITY COM UM USUARIO DENTO
		// O FINDBYID ESPERA UM LONG E RETORNA UM USUARIO DENTRO DE UM OPTIONAL
		// IF E ELSE: SE USUARIO EXSITE, RETORNA MENSAGEM 200 (status 200) COM O USUARIO REQUISITADO (body). SENAO, RETORNA UMA RESPOSTA NO CONTENT
		// GET MAPPING POIS SE QUER PEGAR ALGO DO BANCO DE DADOS
		// PRECISA-SE PASSAR O ID POSTAGEM A SER PROCURADO, ISSO É FEITO PELO @PATHVARIABLE
		// PATHVARIABLE PEGA O ID PASSADO PELA ROTA
		// PARA INDICAR QUE O PATH PEGARÁ A VARIÁVEL PELO CAMINHO , PRECISA-SE APONTAR AONDE QUE SE QUER QUE PEGUE E EM QUAL FORMATO
		// ISSO É PASSADO DEPOIS DO GETMAPPING ("/{ID_USUARIO}")
		// UM OUTRO CAMINHO PARA FIND BY ID EH CRIAR O METODO NA INTERFACE REPOSITORIO E CHAMAR (QUE NEM FOI FEITO COM O FIND BY ID DAS POSTAGEM)
	
	@GetMapping ("/{id_usuario}")
	
	public ResponseEntity <Usuarios> buscarPorId (@PathVariable (value = "id_usuario") Long idUsuario){ 
		Optional <Usuarios> objetoUsuarios = repositorio.findById(idUsuario);
		
		if(objetoUsuarios.isPresent()) 
		{
			return ResponseEntity.status(200).body (objetoUsuarios.get());
		}
		else 
		{
			return ResponseEntity.status(204).build();
		}
			
}
	
		// AQUI SE TEM O SEGUNDO MODO DE GET BY ID (FOI DECLARADO O METODO NA INTERFACE E CHAMADO AQUI)
		// ESSE METODO TAMBÉM FOI DOCUMENTADO
	
	@GetMapping ("/{id_usuario} 2")
	public ResponseEntity <List<Usuarios>> buscarPorNome (String nome){
		List<Usuarios> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);
		
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(objetoLista);
		}
		
	}
	//AQUI ATUALIZAR UM DADO
	// PROCURA PELO ID E ALTERAR NO BANCO AO MESMO TEMPO
	//MESMA IDEIA DO SAVE, ENTRETANTO, O VERBO HHTP É PUT E SAVE É POST
	
	/*@PutMapping ("/atualizar")
	public ResponseEntity<Usuarios> atualizar (@Valid @RequestBody Usuarios usuarioParaAtualizar){
	
		return ResponseEntity.status(201).body(repositorio.save(usuarioParaAtualizar));
}*/
	
	
	
	
	@PutMapping("/atualizar") 
    public ResponseEntity<Object> changeUser(@Valid @RequestBody UsuarioDTO userChanges) {
        Optional<?> objectUpdate = servicos.changeUser(userChanges);

        if (objectUpdate.isPresent()) {
            return ResponseEntity.status(201).body(objectUpdate.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id de usuario invalido!", null);
        }
    }
	
	//DEVOLVE VOID E RECEBE COMO PARAMETRO UM LONG PASSADO PELO ID DO USUARIO
	// NÃO RETORNA NADA, SO DELETA E ACABOU
	//
	
	@DeleteMapping ("/deletar/{id_usuario}")
	public void deletarUsuarioPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		repositorio.deleteById(idUsuario);
	}
	
	
	
	@GetMapping ("/pesquisa/{nome}")
	public ResponseEntity <List<Usuarios>> buscaPorNomeII(@RequestParam(defaultValue = "") String nome){
		List<Usuarios> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);
		
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuarios novoUsuario) {
		Optional <Object> objetoOptional = servicos.cadastrarUsuario(novoUsuario);
		
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
			
		}else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
		
	}
	
	@PutMapping("/credenciais")
	public ResponseEntity <Object> credenciais (@Valid @RequestBody UsuarioDTO usuarioParaAutenticar) {
		Optional <?> objetoOptional = servicos.pegarCredenciais(usuarioParaAutenticar);
		
		if(objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		}else {
			return ResponseEntity.status(201).body (objetoOptional.get());
		}
	}
}
