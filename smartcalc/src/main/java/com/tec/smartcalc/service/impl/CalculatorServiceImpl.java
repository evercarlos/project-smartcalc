package com.tec.smartcalc.service.impl;

import com.google.gson.Gson;
import com.tec.smartcalc.domain.dto.CalculatorParameterRequestDto;
import com.tec.smartcalc.domain.dto.PercentageResponseDto;
import com.tec.smartcalc.exception.TransactionException;
import com.tec.smartcalc.helper.CallHistoryHelper;
import com.tec.smartcalc.service.CalculatorService;
import com.tec.smartcalc.service.CallHistoryService;
import com.tec.smartcalc.service.ExternalCalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculatorServiceImpl implements CalculatorService {

    @Value("${tec.external.endPoint.percentage}")
    private String endpoint;

    @Value("${tec.external.endPoint.percentage.getPercentage}")
    private String method;

    private final ExternalCalculateService externalService;
    private final CallHistoryService callHistoryService;

    @Override
    public double calculatePercentage(CalculatorParameterRequestDto request) {
        log.info("[CalculatorServiceImpl] start process");

        LocalDateTime date = LocalDateTime.now();
        String parameterJson = new Gson().toJson(request);
        try {
            PercentageResponseDto response = externalService.getPercentage();

            double sum = request.numberOne() + request.numberTwo();
            double valueCalculate = sum + (sum * response.percentage() / 100);

            callHistoryService.saveAsync(CallHistoryHelper.loadData(date, endpoint + method, parameterJson, valueCalculate,
                    Boolean.FALSE.equals(response.status()) ? response.message() : null));

            return valueCalculate;
        } catch (Exception ex) {
            callHistoryService.saveAsync(CallHistoryHelper.loadData(date, endpoint + method, parameterJson, 0, ex.getMessage()));
            log.error("[CalculatorServiceImpl] error calculator percentage {}", ex.getMessage(), ex.getCause());
            throw new TransactionException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
