package ch.es.pl.users;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DAOConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    public UserDAO userDAO() {
        if (url.contains("mysql")) {
            return new UserDAOMySQL();
        } else if (url.contains("oracle")) {
            return new UserDAOOracle();
        }
        return null;
    }
}

