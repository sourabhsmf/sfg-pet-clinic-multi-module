package guru.springframework.sfgpetclinic.model;

import java.sql.Date;

/**
 * 
 */
public class Pet extends BaseEntity {

    private Date dob;
    private Owner owner;
    private PetType petType;
    private String petName;
    private String disease;

    public Pet(String petName, String disease) {
        super();
        this.petName = petName;
        this.disease = disease;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setPetName(String petName) {
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
