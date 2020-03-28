package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import guru.springframework.sfgpetclinic.validators.AddressConstraint;
import lombok.*;
import org.hibernate.validator.constraints.Length;


/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
 public class Owner extends Person {

    @Column(name = "address")
    @AddressConstraint
    @NotBlank
    private String address;
    
    @Column(name = "telephone")
    @NotBlank(message = "Telephone cannot be blank")
    @Length(min = 10, max = 10, message = "Length should be 10 digits")
    private String telephone;
    
    @Column(name = "city")
    @NotBlank(message = "City cannot be blank")
    private String city;
    
    @Column(name = "age")
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Integer age;
    
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
   
    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String telephone, String city, Integer age, Set<Pet> pets) {
       super(id, firstName, lastName);
       this.address = address;
       this.telephone = telephone;
       this.city = city;
       this.age = age;
       this.pets = pets;
    }
 
}
