package CivilRegistryOffice.CivilSystem.business.rules;

import org.springframework.stereotype.Service;

import CivilRegistryOffice.CivilSystem.dataRepository.PersonRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BeforeCreateTcKimlikNumberRule {
    private PersonRepository personRepository;
    
   
    public boolean checkCreateTcKimlikNumberBefore(String tcKimlikNumber) {
        return personRepository.existsByTcKimlikNumber(tcKimlikNumber);
    }
}