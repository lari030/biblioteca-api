package com.larissa.meuprojeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.larissa.meuprojeto.repository.UsuarioRepository;

@Service
public class AutorizacaoService implements UserDetailsService{
  
//utilizado pelo Spring Security durante o processo de autenticação
//para buscar o usuário no banco de dados.
@Autowired
UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return usuarioRepository.findByLogin(username);
    }
    
}
