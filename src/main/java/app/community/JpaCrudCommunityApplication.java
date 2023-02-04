package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class JpaCrudCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaCrudCommunityApplication.class, args);
    }


    // TODO : 추후에 세션에서 ID값 가져오는 로직으로 변경해야함.
    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(1L);
    }

}
