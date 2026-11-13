package org.butterfp.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class CalculatorControllerTest {

    private final CalculatorController controller =
            new CalculatorController();

    @Test
    @DisplayName("Десятичный логарифм 100 равен 2")
    void logarithmOfHundredReturnsTwo() {
        assertEquals(
                2.0,
                controller.logarithm(100),
                1e-9,
                "Десятичный логарифм 100 должен равняться 2"
        );
    }

    @Test
    @DisplayName("Десятичный логарифм 1000 равен 3")
    void logarithmOfThousandReturnsThree() {
        assertEquals(
                3.0,
                controller.logarithm(1000),
                1e-9,
                "Десятичный логарифм 1000 должен равняться 3"
        );
    }

    @Test
    @DisplayName("Десятичный логарифм 1 равен 0")
    void logarithmOfOneReturnsZero() {
        assertEquals(
                0.0,
                controller.logarithm(1),
                1e-9,
                "Десятичный логарифм 1 должен равняться 0"
        );
    }

    @Test
    @DisplayName("Отрицательный аргумент вызывает исключение")
    void logarithmRejectsNegativeNumber() {
        assertThrows(
                ResponseStatusException.class,
                () -> controller.logarithm(-10),
                "Логарифм отрицательного числа должен вызывать исключение"
        );
    }

    @Test
    @DisplayName("Отрицательный аргумент возвращает HTTP 400")
    void logarithmReturnsBadRequestForNegativeNumber() {
        ResponseStatusException exception = catchThrowableOfType(
                ResponseStatusException.class,
                () -> controller.logarithm(-10)
        );

        assertEquals(
                HttpStatus.BAD_REQUEST,
                exception == null ? null : exception.getStatusCode(),
                "Некорректный аргумент должен приводить к HTTP 400"
        );
    }

    @Test
    @DisplayName("Нулевой аргумент недопустим")
    void logarithmRejectsZero() {
        assertThrows(
                ResponseStatusException.class,
                () -> controller.logarithm(0),
                "Логарифм нуля не определён"
        );
    }
}