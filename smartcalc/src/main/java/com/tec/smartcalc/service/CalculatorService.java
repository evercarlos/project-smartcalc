package com.tec.smartcalc.service;

import com.tec.smartcalc.domain.dto.CalculatorParameterRequestDto;

public interface CalculatorService {

    double calculatePercentage(CalculatorParameterRequestDto calculatorParameterRequestDto);
}
