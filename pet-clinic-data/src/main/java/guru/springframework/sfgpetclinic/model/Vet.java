package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * 
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Column(name = "years_of_practice")
    private Date yearsOfPractice;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities" , joinColumns = @JoinColumn(name="vet_id") , 
                inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
    
    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "city")
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
