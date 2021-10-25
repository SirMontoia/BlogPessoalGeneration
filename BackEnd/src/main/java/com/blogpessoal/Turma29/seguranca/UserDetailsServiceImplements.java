package com.blogpessoal.Turma29.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Usuarios;
import com.blogpessoal.Turma29.repository.UsuariosRepositorio;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	
	private @Autowired UsuariosRepositorio repositorio;
	
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
		Optional<Usuarios> objetoOptional = repositorio.findByEmail(username);
		
		if(objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());
		}else {
			throw new UsernameNotFoundException(username + "Não existe!");
		}
	}
}

