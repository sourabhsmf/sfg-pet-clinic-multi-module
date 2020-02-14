package guru.springframework.sfgpetclinic.model;


/**
 * 
 */
public class Owner extends Person {

    public Integer age;
    
    public Owner(Integer age) {
        super();
        this.age = age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public Integer getAge(){
        return this.age;
    }
}
