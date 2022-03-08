package ch.es.pl.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject(
                    "select password from users where login=?",
                    new Object[]{login},
                    (rs, rownum) ->
                            new User(
                                    login,
                                    rs.getString("password")
                            )
            );
        } catch (EmptyResultDataAccessException e) {
            throw new IncorrectLoginException(login);
        }
    }

    public int save(User user) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(
                                "insert into users (login,password) values(?,?)",
                                Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, user.getLogin());
                        ps.setString(2, user.getPassword());
                        return ps;
                    },
                    keyHolder
            );
            return keyHolder.getKey().intValue();
        } catch (DuplicateKeyException e) {
            throw new DuplicateLoginException(user.getLogin());
        }
    }
}
