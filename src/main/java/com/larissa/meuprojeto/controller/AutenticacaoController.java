package com.larissa.meuprojeto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.larissa.meuprojeto.data.dto.request.AutenticacaoRequest;
import com.larissa.meuprojeto.data.dto.request.RegistroRequest;
import com.larissa.meuprojeto.data.dto.response.LoginResponse;
import com.larissa.meuprojeto.data.entity.Usuario;
import com.larissa.meuprojeto.infra.security.TokenService;
import com.larissa.meuprojeto.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "Endpoints relacionados à autenticação de usuários")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Operation(
        summary = "Login de usuário",
        description = "Autentica um usuário com login e senha, retornando um token JWT.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Login ou senha inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AutenticacaoRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
            request.login(), request.password()
        );
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @Operation(
        summary = "Registro de novo usuário",
        description = "Registra um novo usuário no sistema com login, senha e role.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PreAuthorize("#request.role != T(com.larissa.meuprojeto.data.entity.UsuarioRole).ADMIN")
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody @Valid RegistroRequest request) {
        if (usuarioRepository.findByLogin(request.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        var encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        Usuario newUsuario = new Usuario(request.login(), encryptedPassword, request.role());
        this.usuarioRepository.save(newUsuario);

        return ResponseEntity.ok().build();
    }
}
