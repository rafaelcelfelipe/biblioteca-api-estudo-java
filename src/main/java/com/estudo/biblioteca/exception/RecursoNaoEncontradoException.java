package com.estudo.biblioteca.exception;

import java.lang.RuntimeException;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message){
        super(message);
    };

}