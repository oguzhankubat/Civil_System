package CivilRegistryOffice.CivilSystem.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CivilRegistryOffice.CivilSystem.DTO.getAllCivilDTO;
import CivilRegistryOffice.CivilSystem.business.abstracts.PersonService;
import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import CivilRegistryOffice.CivilSystem.business.requests.CreateTcKimlikNumberRequest;
import CivilRegistryOffice.CivilSystem.business.responses.CheckTcKimlikNumberResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    
    @GetMapping("/check")
    public CheckTcKimlikNumberResponse getByTcKimlikNumber(
    	@Valid
        @RequestParam String tcKimlikNumber, 
        @RequestParam String corporationVkn) {

        CreateTcKimlikNumberRequest request = new CreateTcKimlikNumberRequest(tcKimlikNumber, corporationVkn);
        return personService.checkPersonByTcKimlikNumber(request);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest) {
        personService.addPerson(createPersonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vatandaş Başarıyla Eklendi.");
    }
    
    @GetMapping("/getall")
    public List<getAllCivilDTO> getAllEntities() {
        return personService.getAllPersons();
    }
}

