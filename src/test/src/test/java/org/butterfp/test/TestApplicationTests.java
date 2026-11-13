package org.butterfp.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TestApplicationTests {

    @Test
    @DisplayName("Контекст приложения регистрирует контроллер калькулятора")
    void contextLoads(@Autowired CalculatorController controller) {
        assertNotNull(controller, "Контекст приложения регистрирует контроллер калькулятора");
    }

}
