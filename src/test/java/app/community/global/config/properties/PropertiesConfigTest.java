package app.community.global.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertiesConfigTest {

    @Value("#{message['message.member.success.account']}")
    private String MESSAGE_SUCCESS_ACCOUNT;

    @Test
    void test() {
        System.out.println(MESSAGE_SUCCESS_ACCOUNT);
    }

}