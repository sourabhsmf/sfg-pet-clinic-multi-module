package guru.springframework.sfgpetclinic.model;


/**
 * 
 */
public class PetType extends BaseEntity {

    public String name;
    public PetType(String name) {
        super();
        this.name = name;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
