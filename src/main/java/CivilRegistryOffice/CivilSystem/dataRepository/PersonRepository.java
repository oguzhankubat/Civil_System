package CivilRegistryOffice.CivilSystem.dataRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import CivilRegistryOffice.CivilSystem.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	boolean existsByTcKimlikNumber(String tcKimlikNumber);
	
	Optional <Person> findBytcKimlikNumber(String tcKimlikNumber);
	

}
