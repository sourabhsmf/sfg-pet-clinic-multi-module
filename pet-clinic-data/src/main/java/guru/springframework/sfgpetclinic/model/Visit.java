package guru.springframework.sfgpetclinic.model;

import java.sql.Date;

public class Visit extends BaseEntity {
    public Date date;
    public String description;
    public Pet pet;

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setPet(Pet pet){
        this.pet = pet;
    }
    public Pet getPet(){
        return this.pet;
    }
}