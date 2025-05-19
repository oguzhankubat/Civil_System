package CivilRegistryOffice.CivilSystem.business.concretes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import CivilRegistryOffice.CivilSystem.Core.ModelMapperServices;
import CivilRegistryOffice.CivilSystem.Core.exceptions.BusinessExceptions;
import CivilRegistryOffice.CivilSystem.DTO.getAllCivilDTO;
import CivilRegistryOffice.CivilSystem.business.abstracts.PersonService;
import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import CivilRegistryOffice.CivilSystem.business.requests.CreateTcKimlikNumberRequest;
import CivilRegistryOffice.CivilSystem.business.responses.CheckTcKimlikNumberResponse;
import CivilRegistryOffice.CivilSystem.business.rules.BeforeCreateDateTimeRule;
import CivilRegistryOffice.CivilSystem.business.rules.BeforeCreateGenderRule;
import CivilRegistryOffice.CivilSystem.business.rules.BeforeCreateTcKimlikNumberRule;
import CivilRegistryOffice.CivilSystem.dataRepository.PersonQueryRepository;
import CivilRegistryOffice.CivilSystem.dataRepository.PersonRepository;
import CivilRegistryOffice.CivilSystem.entities.Person;
import CivilRegistryOffice.CivilSystem.entities.PersonQuery;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonManager implements PersonService{
	private PersonRepository personRepository;
	private ModelMapperServices modelMapperServices;
	private BeforeCreateTcKimlikNumberRule beforeCreateTcKimlikNumberRule;
	private BeforeCreateGenderRule beforeCreateGenderRule;
	private BeforeCreateDateTimeRule beforeCreateDateTimeRule;
	private PersonQueryRepository personQueryRepository;
	
	
	
    public CheckTcKimlikNumberResponse checkPersonByTcKimlikNumber(CreateTcKimlikNumberRequest createTcKimlikNumberRequest) {
    	Optional<Person> personOptional = personRepository.findBytcKimlikNumber(createTcKimlikNumberRequest.getTcKimlikNumber());
        
        Person person = personOptional
                .orElseThrow(() -> new BusinessExceptions("TC Kimlik Numarası Bulunamadı: " + createTcKimlikNumberRequest.getTcKimlikNumber()));
 
        PersonQuery personQuery = new PersonQuery();
        personQuery.setCorporationVkn(createTcKimlikNumberRequest.getCorporationVkn());
        personQuery.setQueryTime(LocalDateTime.now());
       
        personQuery.setPersonTcKimlikNumber(person);
        
        personQueryRepository.save(personQuery);
        
        
        return modelMapperServices.forResponse()
                .map(person, CheckTcKimlikNumberResponse.class);
           
    };

	@Override
    public void addPerson(CreatePersonRequest createPersonRequest) {
		beforeCreateGenderRule.checkRequestGender(createPersonRequest);
		beforeCreateDateTimeRule.checkRequestDateTime(createPersonRequest);
		
        Person person = modelMapperServices.forRequest()
        		.map(createPersonRequest, Person.class);
        
        //normalde map ile hepsini setleyebiliyoruz fakat öğrenme aşamasında olması açısından
        //ileri seviye reflection denilen yöntemi kullanmamak adına manuel küçük harfe çeviriyorum.
        
        person.setPersonName(person.getPersonName().toLowerCase());
        person.setPersonLastName(person.getPersonLastName().toLowerCase());
        person.setGender(person.getGender().toLowerCase());
        person.setCreatedTime(LocalDateTime.now());
        person.setTcKimlikNumber(generateTcKimlikNumber());

        personRepository.save(person);
 
    }
    public String generateTcKimlikNumber() {
        String tcKimlikNumber = createTcKimlikNumber();  
    
        while (beforeCreateTcKimlikNumberRule.checkCreateTcKimlikNumberBefore(tcKimlikNumber)) {
            tcKimlikNumber = createTcKimlikNumber(); 
        }

        return tcKimlikNumber; 
    }


    private String createTcKimlikNumber() {
        Random random = new Random();
        List<Integer> rakamlar = new ArrayList<>();


        rakamlar.add(random.nextInt(9) + 1);


        for (int i = 0; i < 8; i++) {
            rakamlar.add(random.nextInt(10));
        }

        int tekToplam = 0;
        int ciftToplam = 0;

        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                tekToplam += rakamlar.get(i);
            } else { 
                ciftToplam += rakamlar.get(i);
            }
        }

       
        int onuncuHane = (tekToplam * 7 - ciftToplam) % 10;
        rakamlar.add(onuncuHane);


        int toplam = 0;
        for (int i = 0; i < 10; i++) {
            toplam += rakamlar.get(i);
        }
        int onbirinciHane = toplam % 10;
        rakamlar.add(onbirinciHane);

    
        StringBuilder tcKimlikNo = new StringBuilder();
        for (int rakam : rakamlar) {
            tcKimlikNo.append(rakam);
        }

        return tcKimlikNo.toString(); 
    }
    
    @Override
    public List<getAllCivilDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll(); 

   
        List<getAllCivilDTO> personDTOs = persons.stream()
        	    .map(person -> modelMapperServices.forResponse().map(person, getAllCivilDTO.class))
        	    .collect(Collectors.toList());

        savePersonsToJson(personDTOs);                        

        return personDTOs;                                
    }

    private void savePersonsToJson(List<getAllCivilDTO> persons) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File directory = new File("data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            objectMapper.writeValue(new File(directory, "persons.json"), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
