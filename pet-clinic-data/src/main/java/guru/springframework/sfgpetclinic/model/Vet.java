package guru.springframework.sfgpetclinic.model;

import java.sql.Date;

/**
 * 
 */
public class Vet extends Person {

    public Date yearsOfPractice;
    public Vet(Date yearsOfPractice) {
        this.yearsOfPractice = yearsOfPractice;
    }
    public void setYearsOfPractice (Date yearsOfPractice){
        this.yearsOfPractice = yearsOfPractice;
    }
    public Date getYearsOfPractice(){
        return this.yearsOfPractice;
    }
}
