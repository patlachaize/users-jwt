package ch.es.pl.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @PostMapping(value="/tokens")
    public ResponseEntity<String> requestToken(@RequestBody User user) {
        User user2 = userDAO.findByLogin(user.getLogin());
        if (! user.getPassword().equals(user2.getPassword())) {
            throw new IncorrectLoginException(user.getLogin());
        }
        String token = jwtTokenUtil.generateToken(user.getLogin());
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
