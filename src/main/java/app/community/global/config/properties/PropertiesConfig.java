package app.community.global.config.properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * message.properties 를 spEL 로 접근하여 사용하기 위한 bean 등록.
 */
@Configuration
public class PropertiesConfig {

    @Bean(name = "message")
    public PropertiesFactoryBean propertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("message.properties");

        propertiesFactoryBean.setLocation(classPathResource);
        return propertiesFactoryBean;
    }
}
