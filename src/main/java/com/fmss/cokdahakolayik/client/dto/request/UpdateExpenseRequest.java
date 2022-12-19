package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateExpenseRequest(@NotBlank(message = "Harcama türü boş olamaz.")String type,
                                   @Min(value = 1, message = "Harcama miktarı daha büyük olmalı.")Double amount,
//                                   @JsonFormat(pattern = "dd-MM-yyyy")
                                   LocalDate receiptDate,
                                   @NotBlank(message = "Harcama açıklaması boş olamaz.")String description,
                                   @NotNull(message = "Beklenmeyen hata oluştu.") Long employeeId) {
}
