package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateOvertimeRequest(LocalDate overtimeDate,
                                    @Min(value = 1, message = "Mesai suresi daha buyuk olmali.") Double amountOvertime,
                                    @NotBlank(message = "Mesai açiklamasi bos olamaz.") String description,
                                    @NotNull(message = "Beklenmeyen hata olustu.")Long employeeId) {
}
