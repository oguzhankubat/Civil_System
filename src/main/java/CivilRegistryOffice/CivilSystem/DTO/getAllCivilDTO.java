package CivilRegistryOffice.CivilSystem.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class getAllCivilDTO {

    private String personName;


    private String personLastName;

    private String tcKimlikNumber;


    private String gender;
    

    private LocalDate birthDate;

    private String birthPlace;

    private String residenceAdress;
}
