package org.butterfp.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return Math.addExact(a, b);
    }

    @GetMapping("/subtract")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        return a - b;
    }

    @GetMapping("/multiply")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return a * b;
    }

    @GetMapping("/remainder")
    public int remainder(@RequestParam int a, @RequestParam int b) {
        if (b == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Деление на ноль запрещено"
            );
        }

        return a % b;
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Деление на ноль запрещено"
            );
        }

        return a / b;
    }

    @GetMapping("/logarithm")
    public double logarithm(@RequestParam double number) {
        if (!Double.isFinite(number) || number <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Аргумент логарифма должен быть положительным числом"
            );
        }

        return Math.log10(number);
    }

    @GetMapping("/sin")
    public double sin(@RequestParam double angle) {
        if (!Double.isFinite(angle)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Угол должен быть конечным числом"
            );
        }

        return Math.sin(angle);
    }

    @GetMapping("/cos")
    public double cos(@RequestParam double angle) {
        if (!Double.isFinite(angle)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Угол должен быть конечным числом"
            );
        }

        return Math.cos(angle);
    }

    @GetMapping("/tg")
    public double tg(@RequestParam double angle) {
        if (!Double.isFinite(angle)
                || Math.abs(Math.cos(angle)) < 1e-12) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Тангенс не определён для данного угла"
            );
        }

        return Math.tan(angle);
    }
    private double memory = 0.0;

    @PostMapping("/memory/save")
    public double saveToMemory(@RequestParam double value) {
        memory = value;
        return memory;
    }

    @GetMapping("/memory/read")
    public double readFromMemory() {
        return memory;
    }

    @GetMapping("/percentage")
    public double percentage(@RequestParam double number,
                             @RequestParam double percent) {
        return number * percent / 100.0;
    }

    @GetMapping("/memory/current")
    public double getCurrentMemoryValue() {
        return memory;
    }
}
