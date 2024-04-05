package vitor.projeto.Control.Persistence.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class FormatoInvalidoException extends RuntimeException {
    public FormatoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatoInvalidoException(String message) {
        super(message);
    }

    public FormatoInvalidoException(Throwable cause) {
        super(cause);
    }
}

