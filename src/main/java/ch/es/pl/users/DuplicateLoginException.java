package ch.es.pl.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateLoginException extends RuntimeException {
    public DuplicateLoginException(String login) {
        super("Login "+ login + " déjà pris");
    }
}

