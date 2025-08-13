package com.larissa.meuprojeto.data.dto.request;

import com.larissa.meuprojeto.data.entity.UsuarioRole;

public record RegistroRequest(String login, String password, UsuarioRole role) {
    
}
