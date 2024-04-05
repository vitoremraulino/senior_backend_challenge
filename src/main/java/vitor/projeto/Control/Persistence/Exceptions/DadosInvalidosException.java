package vitor.projeto.Control.Persistence.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }

    public DadosInvalidosException(String message) {
        super(message);
    }

    public DadosInvalidosException(Throwable cause) {
        super(cause);
    }
}

