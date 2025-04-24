package CivilRegistryOffice.CivilSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTcKimlikNumberRequest {
	
    @NotBlank
    @Size(min = 11, max = 11)
    private String tcKimlikNumber;
    
    @NotBlank
    @Size(min = 10,max = 10)
    private String corporationVkn;
    
}


