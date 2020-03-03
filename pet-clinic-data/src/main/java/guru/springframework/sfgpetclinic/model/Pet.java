package guru.springframework.sfgpetclinic.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Column(name = "dob")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "disease")
    private String disease;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet(String petName, String disease) {
        super();
        this.petName = petName;
        this.disease = disease;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
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
