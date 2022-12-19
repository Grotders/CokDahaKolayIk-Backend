package com.fmss.cokdahakolayik.client.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OvertimeDto(Long id,
                          LocalDate overtimeDate,
                          Double amountOvertime,
                          String description) {
}
