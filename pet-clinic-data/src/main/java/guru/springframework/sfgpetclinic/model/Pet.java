package guru.springframework.sfgpetclinic.model;


/**
 * 
 */
public class Pet extends BaseEntity{

    private String petName;
    private String disease;
    public Pet(String petName, String disease) {
        super();
        this.petName = petName;
        this.disease = disease;
    }
    public void setPetName (String petName){
        this.petName = petName;
    }
    public void setDisease (String disease){
        this.disease = disease;
    }
    public String getPetName(){
        return this.petName;
    }
    public String getDisease(){
        return this.disease;
    }
}
