package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import guru.springframework.sfgpetclinic.validators.AddressConstraint;
import lombok.*;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Column(name = "years_of_practice")
    @NotNull(message = "Years of Practice cannot be blank")
    private Date yearsOfPractice;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities" , joinColumns = @JoinColumn(name="vet_id") , 
                inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
    
    @Column(name = "address")
    @AddressConstraint
    @NotBlank(message = "Address can not be blank")
    private String address;

    @Column(name = "telephone")
    @NotBlank(message = "Telephone cannot be blank")
    @Length(min = 10, max = 10, message = "Length should be 10 digits")
    private String telephone;
    
    @Column(name = "city")    
    @NotBlank(message = "City cannot be blank")
    @Pattern(regexp = "([A-Z a-z])+", message = "City must contain letters only")
    private String city;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "vet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Vet(Long Id, String firstName, String lastName, Date yearsOfPractice, Set<Speciality> specialities,
               String address, String telephone, String city) {
        super(Id, firstName, lastName);
        this.yearsOfPractice = yearsOfPractice;
        this.specialities = specialities;
        this.address = address;
        this.telephone = telephone;
        this.city = city;
    }
}
