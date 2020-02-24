package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class Owner extends Person {

    private String address;
    private String telephone;
    private String city;
    private Integer age;
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
