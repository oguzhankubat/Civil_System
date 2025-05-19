package CivilRegistryOffice.CivilSystem.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckTcKimlikNumberResponse {
	private String personName;
	private String personLastName;
	private String tcKimlikNumber;
	private char gender;
	private LocalDate birthDate;
	private String birthPlace;
	private String residenceAdress;
}
