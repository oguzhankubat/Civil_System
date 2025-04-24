package CivilRegistryOffice.CivilSystem.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "persons",indexes = {
	    @Index(name = "idx_tc_kimlik_number", columnList = "tcKimlikNumber")
	})
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "person_name", nullable = false,length = 35)
    private String personName;

    @Column(name = "person_lastname", nullable = false,length = 20)
    private String personLastName;

    @Column(name = "tc_kimlik_number", length = 11, nullable = false, unique = true) 
    private String tcKimlikNumber;

    @Column(name = "gender", nullable = false,length = 1)
    private String gender;
    
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @OneToMany(mappedBy = "personTcKimlikNumber")
    private List<PersonQuery> personQueries;
}
