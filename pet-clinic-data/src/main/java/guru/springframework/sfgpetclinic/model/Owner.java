package guru.springframework.sfgpetclinic.model;

import java.util.Set;

/**
 * 
 */
public class Owner extends Person {

    public Integer age;
    public Set<Pet> pets;
    
    public Owner(Integer age) {
        this.age = age;
    }
    public void setAge(Integer age){
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
