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
}