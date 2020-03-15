package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.*;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Speciality extends BaseEntity{
    @Column(name = "description")
    private String description;

    @Builder
    public Speciality(Long Id, String description) {
        super(Id);
        this.description = description;
    }
}