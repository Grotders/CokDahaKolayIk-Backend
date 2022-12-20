package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateLeaveRequest(@NotBlank(message = "Izin tipi bos olamaz.") String type,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 @NotBlank(message = "Izin aciklamasi bos olamaz.") String description,
                                 @NotNull(message = "Beklenmeyen hata olustu.")Long employeeId) {
}
