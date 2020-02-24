package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class Vet extends Person {

    private Date yearsOfPractice;
    private Set<Speciality> specialities = new HashSet<>();
    private String address;
    private String telephone;
    private String city;

    public Vet(Date yearsOfPractice) {
        this.setYearsOfPractice(yearsOfPractice);
    }

    public Date getYearsOfPractice() {
        return yearsOfPractice;
    }

    public void setYearsOfPractice(Date yearsOfPractice) {
        this.yearsOfPractice = yearsOfPractice;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
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

}
