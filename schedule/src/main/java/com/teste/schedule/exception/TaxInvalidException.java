package com.teste.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Data inválida para o cálculo da taxa")
public class TaxInvalidException extends RuntimeException {

    public TaxInvalidException(String mensagem) {
        super(mensagem);
    }

    public TaxInvalidException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}