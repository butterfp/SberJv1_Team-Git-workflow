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

    @Test
    void sinOfZeroReturnsZero() {
        assertEquals(
                0.0,
                controller.sin(0),
                1e-9,
                "Синус нуля должен равняться нулю"
        );
    }

    @Test
    void sinOfHalfPiReturnsOne() {
        assertEquals(
                1.0,
                controller.sin(Math.PI / 2),
                1e-9,
                "Синус PI/2 должен равняться единице"
        );
    }

    @Test
    void cosOfZeroReturnsOne() {
        assertEquals(
                1.0,
                controller.cos(0),
                1e-9,
                "Косинус нуля должен равняться единице"
        );
    }

    @Test
    void cosOfPiReturnsMinusOne() {
        assertEquals(
                -1.0,
                controller.cos(Math.PI),
                1e-9,
                "Косинус PI должен равняться минус единице"
        );
    }

    @Test
    void tgOfQuarterPiReturnsOne() {
        assertEquals(
                1.0,
                controller.tg(Math.PI / 4),
                1e-9,
                "Тангенс PI/4 должен равняться единице"
        );
    }

    @Test
    void tgOfHalfPiThrowsException() {
        assertThrows(
                ResponseStatusException.class,
                () -> controller.tg(Math.PI / 2),
                "Тангенс PI/2 не определён"
        );
    }

    @Test
    void sinRejectsNaN() {
        assertThrows(
                ResponseStatusException.class,
                () -> controller.sin(Double.NaN),
                "NaN не должен приниматься как угол"
        );
    }

    @Test
    @DisplayName("Расчёт процента от числа")
    void percentageTest() {
        assertEquals(25.0, controller.percentage(200, 12.5), 0.0001,
                "Должен вычисляться процент от числа");
    }

    @Test
    @DisplayName("Расчёт нулевого процента")
    void percentageZeroTest() {
        assertEquals(0.0, controller.percentage(200, 0), 0.0001,
                "Нулевой процент должен давать ноль");
    }

    @Test
    @DisplayName("Расчёт ста процентов")
    void percentageHundredTest() {
        assertEquals(200.0, controller.percentage(200, 100), 0.0001,
                "Сто процентов должны возвращать исходное число");
    }

    @Test
    @DisplayName("Расчёт отрицательного процента")
    void percentageNegativeTest() {
        assertEquals(-20.0, controller.percentage(200, -10), 0.0001,
                "Отрицательный процент должен обрабатываться корректно");
    }

    @Test
    @DisplayName("Расчёт дробного процента")
    void percentageFractionTest() {
        assertEquals(2.5, controller.percentage(100, 2.5), 0.0001,
                "Дробный процент должен вычисляться корректно");
    }

}