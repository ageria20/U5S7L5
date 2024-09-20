package ageria.U5S7L5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {


    @Bean
    PasswordEncoder pswEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
