package ch.es.pl.users;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping(value = "/users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        System.out.println("user to create: " + user);
        int id = userDAO.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value="/tokens")
    public ResponseEntity<String> requestToken(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password) {
        User user2 = userDAO.findByLogin(login);
        if (! password.equals(user2.getPassword())) {
            throw new IncorrectLoginException(login);
        }
        String token = Jwts.builder().setSubject(login).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
