package com.larissa.meuprojeto.repository;

import com.larissa.meuprojeto.data.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
UserDetails findByLogin(String login);
    
} 