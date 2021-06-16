package ch.es.pl.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public abstract int save(User user);

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
}
