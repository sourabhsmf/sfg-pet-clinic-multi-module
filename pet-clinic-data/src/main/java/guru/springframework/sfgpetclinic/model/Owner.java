package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "owners")
 public class Owner extends Person {

    @Column(name = "address")
    private String address;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "age")
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
