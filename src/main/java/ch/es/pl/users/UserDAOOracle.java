package ch.es.pl.users;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserDAOOracle extends UserDAO {

    public int save(User user) {
        try {
            int sequence = jdbcTemplate.queryForObject(
                    "select seq_users.nextval from dual",
                    Integer.class
            );
            jdbcTemplate.update(
                    "insert into users (id,login,password) values(?,?,?)",
                    sequence,
                    user.getLogin(),
                    user.getPassword()
            );
            return sequence;
        } catch (DuplicateKeyException e) {
            throw new DuplicateLoginException(user.getLogin());
        }
    }

}
