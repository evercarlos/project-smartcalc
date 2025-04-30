package com.tec.smartcalc.domain.dto;

public record PercentageResponseDto(
        double percentage,
        String message,
        boolean status
) {

}