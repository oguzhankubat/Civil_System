package CivilRegistryOffice.CivilSystem.business.rules;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BeforeCreateDateTimeRule {
	
    public void checkRequestDateTime(CreatePersonRequest createPersonRequest) {
        LocalDate time=createPersonRequest.getBirthDate();
        if (!time.isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Doğum Günü İleri Tarih Olamaz!");
        }
    }

}
