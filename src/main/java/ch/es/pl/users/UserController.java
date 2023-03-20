package ch.es.pl.users;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Value("${jwt.secret}")
    private String secretKey;



    @GetMapping(value="/tokens")
    public ResponseEntity<String> requestToken(
            @RequestHeader("login") String login,
            @RequestHeader("password") String password) {
        Optional<UserEntity> opt= usersRepository.findByLoginAndPassword(login,password);
        if (opt.isEmpty()) {
            throw new IncorrectLoginException(login);
        }
        String token = Jwts.builder().setSubject(login).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
