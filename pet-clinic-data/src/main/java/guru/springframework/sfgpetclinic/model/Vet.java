package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.Set;

/**
 * 
 */
public class Vet extends Person {

    public Date yearsOfPractice;
    public Set<Speciality> specialities; 
    public Vet(Date yearsOfPractice) {
        this.yearsOfPractice = yearsOfPractice;
    }
    public void setYearsOfPractice (Date yearsOfPractice){
        this.yearsOfPractice = yearsOfPractice;
    }
    public Date getYearsOfPractice(){
        return this.yearsOfPractice;
    }
    public void setSpecialities(Set<Speciality> specialities){
        this.specialities = specialities;
    }
    public Set<Speciality> getSpecialities(){
        return this.specialities;
    }
}
