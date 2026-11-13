package org.butterfp.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorControllerTest {

    private final CalculatorController controller =
            new CalculatorController();

    @Test
    @DisplayName("Логарифм 8 по основанию 2 равен 3")
    void logarithmReturnsCorrectResultForBaseTwo() {
        assertEquals(3.0, controller.logarithm(8, 2), 1e-9, "Логарифм 8 по основанию 2 равен 3");
    }

    @Test
    @DisplayName("Логарифм 100 по основанию 10 равен 2")
    void logarithmReturnsCorrectResultForBaseTen() {
        assertEquals(2.0, controller.logarithm(100, 10), 1e-9, "Логарифм 100 по основанию 10 равен 2");
    }

    @Test
    @DisplayName("Отрицательный аргумент приводит к HTTP 400")
    void logarithmRejectsInvalidArguments() {
        ResponseStatusException exception = catchThrowableOfType(
                ResponseStatusException.class,
                () -> controller.logarithm(-8, 2)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception == null ? null : exception.getStatusCode(),
                "Отрицательный аргумент приводит к HTTP 400");
    }

    @Test
    @DisplayName("Основание 1 недопустимо")
    void logarithmRejectsBaseOne() {
        assertThrows(
                ResponseStatusException.class,
                () -> controller.logarithm(8, 1),
                "Основание 1 недопустимо"
        );
    }
}
