package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Column(name = "dob")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "disease")
    private String disease;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long Id, Date dob, Owner owner, PetType petType, String petName, String disease,
                 Set<Visit> visits) {
        super(Id);
        this.dob = dob;
        this.owner = owner;
        this.petType = petType;
        this.petName = petName;
        this.disease = disease;
        this.visits = visits;
    }

}
