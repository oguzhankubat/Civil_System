package CivilRegistryOffice.CivilSystem.business.rules;

import org.springframework.stereotype.Service;

import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BeforeCreateGenderRule {

    public void checkRequestGender(CreatePersonRequest createPersonRequest) {
        String gender = createPersonRequest.getGender();
        if (!gender.equals("m") && !gender.equals("f")) {
            throw new IllegalArgumentException("Geçersiz cinsiyet! Cinsiyet 'm' veya 'f' olmalıdır.");
        }
    }
}
