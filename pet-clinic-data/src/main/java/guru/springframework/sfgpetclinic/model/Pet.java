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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "Date of Birth cannot be blank")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    @NotNull(message = "Please provide a correct value")
    private PetType petType;

    @Column(name = "pet_name")
    @NotBlank(message = "Pet name cannot be blank")
    @Pattern(regexp = "([A-Z a-z])+", message = "Pet name must contain letters only")
    private String petName;

    @Column(name = "disease")
    @NotBlank(message = "Disease cannot be blank")
    @Pattern(regexp = "([A-Z a-z])+", message = "Pet disease must contain letters only")
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
