package com.tec.smartcalc.controller.v1;

import com.tec.smartcalc.domain.dto.CalculatorParameterRequestDto;
import com.tec.smartcalc.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/calculator", produces = "application/json")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;


    @Operation(summary = "Realiza el proceso de cálculo de percentage")
    @GetMapping
    public double calculatePercentage(@ParameterObject CalculatorParameterRequestDto parameters) {
        return calculatorService.calculatePercentage(parameters);
    }
}
