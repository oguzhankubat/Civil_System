package CivilRegistryOffice.CivilSystem.dataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import CivilRegistryOffice.CivilSystem.entities.PersonQuery;

public interface PersonQueryRepository extends JpaRepository<PersonQuery, Long>{

}
