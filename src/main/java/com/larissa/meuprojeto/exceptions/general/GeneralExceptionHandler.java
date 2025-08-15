package com.larissa.meuprojeto.exceptions.general;

import com.larissa.meuprojeto.exceptions.LivroJaEmprestadoExcecao;
import com.larissa.meuprojeto.exceptions.RestErrorMessage;

import java.util.stream.Collectors;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaExcecao.class)
    private ResponseEntity<RestErrorMessage> tratarEntidadeNaoEncontrada(EntidadeNaoEncontradaExcecao exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<RestErrorMessage>> tratarErroDeValidacaoCampos(MethodArgumentNotValidException exception) {
        List<RestErrorMessage> erros = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erroCampo -> new RestErrorMessage(
                        HttpStatus.BAD_REQUEST,
                        erroCampo.getField() + ": " + erroCampo.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(LivroJaEmprestadoExcecao.class)
    private ResponseEntity<RestErrorMessage> tratarLivroJaEmprestado(LivroJaEmprestadoExcecao ex) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

   
   
}