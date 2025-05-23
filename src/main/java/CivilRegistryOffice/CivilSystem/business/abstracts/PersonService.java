package CivilRegistryOffice.CivilSystem.business.abstracts;

import java.util.List;

import CivilRegistryOffice.CivilSystem.DTO.getAllCivilDTO;
import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import CivilRegistryOffice.CivilSystem.business.requests.QueryTcKimlikNumberRequest;
import CivilRegistryOffice.CivilSystem.business.responses.CheckTcKimlikNumberResponse;

public interface PersonService {
	
	CheckTcKimlikNumberResponse checkPersonByTcKimlikNumber(QueryTcKimlikNumberRequest createTcKimlikNumberRequest);
	void addPerson(CreatePersonRequest createPersonRequest);
	String generateTcKimlikNumber();
	List<getAllCivilDTO> getAllPersons();
	

}
