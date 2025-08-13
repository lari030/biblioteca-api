package com.larissa.meuprojeto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larissa.meuprojeto.data.dto.request.AutenticacaoRequest;
import com.larissa.meuprojeto.data.dto.request.RegistroRequest;
import com.larissa.meuprojeto.data.dto.response.LoginResponse;
import com.larissa.meuprojeto.data.entity.Usuario;
import com.larissa.meuprojeto.infra.security.TokenService;
import com.larissa.meuprojeto.repository.UsuarioRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AutenticacaoRequest request ){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    
    }
    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroRequest request){
        if(usuarioRepository.findByLogin(request.login()) != null) return ResponseEntity.badRequest().build();
        var encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        Usuario newUsuario = new Usuario(request.login(), encryptedPassword, request.role());
        this.usuarioRepository.save(newUsuario);
        return ResponseEntity.ok().build();


    }
    }
    
    