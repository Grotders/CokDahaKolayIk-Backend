package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateOvertimeRequest(LocalDate overtimeDate,
                                    @Min(value = 1, message = "Mesai süresi daha büyük olmalı.") Double amountOvertime,
                                    @NotBlank(message = "Mesai açıklaması boş olamaz.") String description,
                                    @NotNull(message = "Beklenmeyen hata oluştu.")Long employeeId) {
}
