package CivilRegistryOffice.CivilSystem.business.abstracts;

import CivilRegistryOffice.CivilSystem.business.requests.CreatePersonRequest;
import CivilRegistryOffice.CivilSystem.business.requests.CreateTcKimlikNumberRequest;
import CivilRegistryOffice.CivilSystem.business.responses.CheckTcKimlikNumberResponse;

public interface PersonService {
	
	CheckTcKimlikNumberResponse checkPersonByTcKimlikNumber(CreateTcKimlikNumberRequest createTcKimlikNumberRequest);
	void addPerson(CreatePersonRequest createPersonRequest);
	String generateTcKimlikNumber();

	

}
