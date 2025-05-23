package CivilRegistryOffice.CivilSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryTcKimlikNumberRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{11}", message = "TC Kimlik Numarası sadece 11 haneli sayılardan oluşmalıdır.")
    private String tcKimlikNumber;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "VKN sadece 10 haneli sayılardan oluşmalıdır.")
    private String corporationVkn;
}


