package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaCrudCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaCrudCommunityApplication.class, args);
    }

}
