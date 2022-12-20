package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateExpenseRequest(@NotBlank(message = "Harcama turu bos olamaz.")String type,
                                   @Min(value = 1, message = "Harcama miktari daha buyuk olmali.")Double amount,
                                   LocalDate receiptDate,
                                   @NotBlank(message = "Harcama aciklamasi bos olamaz.")String description,
                                   @NotNull(message = "Beklenmeyen hata olustu.") Long employeeId) {
}
