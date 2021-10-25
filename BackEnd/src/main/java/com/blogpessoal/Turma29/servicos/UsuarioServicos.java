package com.blogpessoal.Turma29.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.charset.Charset;
import com.blogpessoal.Turma29.model.Usuarios;
import com.blogpessoal.Turma29.model.utilities.UsuarioDTO;
import com.blogpessoal.Turma29.repository.UsuariosRepositorio;
import org.apache.commons.codec.binary.Base64;



@Service
public class UsuarioServicos {
	
	private @Autowired UsuariosRepositorio repositorio;
	
	/*
	 * 
	 * Método utilizado para criação de um novo usuário no sistema
	 * 
	 * @param novoUsuario do tipo Usuarios
	 * 
	 * @return Optional com Usuario Criado 
	 * 
	 * @author Turma 29
	 * 
	 * @since 1.5
	 */
	
	public Optional <Object> cadastrarUsuario(Usuarios novoUsuario){
		return repositorio.findByEmail(novoUsuario.getEmail()).map(usuarioExistente ->{
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(repositorio.save(novoUsuario));
		});
		
	
	
}
	
	/**
	 * Método Utilizado para pegar credenciais do usuario com Tokem (Formato Basic), 
	 * este método sera utilizado para retornar ao front o token utilizado para ter
	 * acesso aos dados do usuario e mantê-lo logado no sistema
	 * 
	 * 
	 * 
	 * @param usuarioParaAutenticar do tipo UsuarioLoginDTO necessario email e senha
	 * para validar
	 * 
	 * 
	 * @return UsuarioLoginDTO preenchido com informações mais o Token
	 * 
	 * @since 1.0
	 * 
	 * 
	 * @author Turma 29
	 */
	
	
	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar){
		return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente ->{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
				
					String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
					byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
					String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);	
					
					usuarioParaAutenticar.setToken (autorizacaoHeader);
					usuarioParaAutenticar.setIdUsuario (usuarioExistente.getIdUsuario());
					usuarioParaAutenticar.setNome (usuarioExistente.getNome());
					usuarioParaAutenticar.setSenha (usuarioExistente.getSenha());
					usuarioParaAutenticar.setFoto (usuarioExistente.getFoto());
					usuarioParaAutenticar.setTipo (usuarioExistente.getTipo());
					return Optional.ofNullable(usuarioParaAutenticar);
					
					
			} else {
				return Optional.empty();
			}
			
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	
	
	public Optional<?> changeUser(UsuarioDTO userChanges) {
        return repositorio.findById(userChanges.getIdUsuario()).map(existingUser -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String result = encoder.encode(userChanges.getSenha());

            existingUser.setTipo(userChanges.getTipo());
            existingUser.setFoto(userChanges.getFoto());
            existingUser.setEmail(userChanges.getEmail());
            existingUser.setNome(userChanges.getNome());
            existingUser.setSenha(result);
            
            return Optional.ofNullable(repositorio.save(existingUser));
        }).orElseGet(() -> {
            return Optional.empty();
        });
    }
}





