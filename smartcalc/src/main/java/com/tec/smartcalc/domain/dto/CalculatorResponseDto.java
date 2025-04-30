package com.tec.smartcalc.domain.dto;

import java.math.BigDecimal;

public record CalculatorResponseDto(
        BigDecimal numberOne,
        BigDecimal numberTwo
) {}
