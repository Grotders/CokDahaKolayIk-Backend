package com.fmss.cokdahakolayik.client.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ExpenseDto(Long id,
                         String type,
                         Double amount,
                         LocalDate receiptDate,
                         String description)  {
}
