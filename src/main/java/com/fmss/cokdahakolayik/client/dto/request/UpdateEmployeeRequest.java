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
public record UpdateEmployeeRequest(@NotBlank(message = "Mail bos olamaz.") String email,
// Personal information:
                                    @NotBlank(message = "Isim bos olamaz.")String firstname,
                                    @NotBlank(message = "Soyad bos olamaz.") String lastname,
                                    LocalDate birthdate,
//Work information:
                                    @Min(value = 1000, message = "Maas daha yuksek olmali.")Double salary,
                                    @NotNull(message = "Departman bos olamaz.")Department department,
                                    LocalDate startWorkDate,
                                    @NotNull(message = "Unvan bos olamaz.") DeveloperLevel developerLevel,
                                    @NotNull(message = "Seviye bos olamaz.") DeveloperTier developerTier,
                                    @NotNull(message = "Rol bos olamaz.")DeveloperTitle developerTitle,
//Contact information:
                                    @NotBlank(message = "Telefon numarasi bos olamaz.")String phoneNumber,
                                    @NotBlank(message = "Adres bos olamaz.")String addressLine,
                                    @NotBlank(message = "Sehir bos olamaz.")String city,
                                    @NotBlank(message = "Ulke bos olamaz.")String country,
                                    @Min(value = 1 ,message = "Posta kodu daha buyuk olmali.") Integer postcode
)  {
}
