package guru.springframework.sfgpetclinic.model;

public class Speciality extends BaseEntity{
    public String description;

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
}