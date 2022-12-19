package com.fmss.cokdahakolayik.client.dto.request;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateEmployeeRequest(@NotBlank(message = "Mail boş olamaz.") String email,
// Personal information:
                                    @NotBlank(message = "İsim boş olamaz.")String firstname,
                                    @NotBlank(message = "Soyad boş olamaz.") String lastname,
                                    LocalDate birthdate,
//Work information:
                                    @Min(value = 1000, message = "Maaş daha yüksek olmalı.")Double salary,
                                    @NotNull(message = "Departman boş olamaz.")Department department,
                                    LocalDate startWorkDate,
                                    @NotNull(message = "Ünvan boş olamaz.") DeveloperLevel developerLevel,
                                    @NotNull(message = "Seviye boş olamaz.") DeveloperTier developerTier,
                                    @NotNull(message = "Rol boş olamaz.")DeveloperTitle developerTitle,
//Contact information:
                                    @NotBlank(message = "Telefon numarası boş olamaz.")String phoneNumber,
                                    @NotBlank(message = "Adres boş olamaz.")String addressLine,
                                    @NotBlank(message = "Şehir boş olamaz.")String city,
                                    @NotBlank(message = "Ülke boş olamaz.")String country,
                                    @Min(value = 1 ,message = "Posta kodu daha büyük olmalı.") Integer postcode
)  {
}
