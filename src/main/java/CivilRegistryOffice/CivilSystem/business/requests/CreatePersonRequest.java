package CivilRegistryOffice.CivilSystem.business.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest { //POJO (Plain Old Java Object) denir. Bilgi için dursun.
	
	@NotNull
	@NotBlank
	@Size(max = 35)
	private String personName;
	
	@NotNull
	@Size(max = 20)
	private String personLastName;
	
	@NotNull
	@NotBlank
	@Size(max = 1)
	private String gender;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	@NotBlank
	private String birthPlace;
	
	@NotNull
	@NotBlank
	private String residenceAdress;
	

}
