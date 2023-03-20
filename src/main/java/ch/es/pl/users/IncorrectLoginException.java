package ch.es.pl.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectLoginException extends RuntimeException {
    public IncorrectLoginException(String login) {
        super ("User " + login + " pas authentifié");
    }
}
