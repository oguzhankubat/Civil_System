package CivilRegistryOffice.CivilSystem.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="person_queries",indexes ={
	    @Index(name = "idx_person_tc_kimlik_number", columnList = "personTcKimlikNumber")
	})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonQuery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name="corporation_vkn_number")
    private String corporationVkn;  //kurum sisteminden alıp entegre edilecek.

    @Column(name="query_time")
    private LocalDateTime queryTime;
	
	@ManyToOne
	@JoinColumn(name = "person_tc_kimlik_number", referencedColumnName = "tc_kimlik_number")
	private Person personTcKimlikNumber;
	
}
