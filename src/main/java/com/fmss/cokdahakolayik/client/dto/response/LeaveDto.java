package com.fmss.cokdahakolayik.client.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LeaveDto(Long id,
                       String type,
                       LocalDate startDate,
                       LocalDate endDate,
                       String description,
                       Double totalDay) {
}
