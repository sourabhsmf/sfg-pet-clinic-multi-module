package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 */
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

    public Owner(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge(){
        return this.age;
    }
    public void setPets(Set<Pet> pets){
        this.pets = pets;
    }
    public Set<Pet> getPets(){
        return this.pets;
    }
}
