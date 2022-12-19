package com.fmss.cokdahakolayik.client.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateLeaveRequest(@NotBlank(message = "İzin tipi boş olamaz.") String type,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 @NotBlank(message = "İzin açıklaması boş olamaz.") String description,
                                 @NotNull(message = "Beklenmeyen hata oluştu.")Long employeeId) {
}
