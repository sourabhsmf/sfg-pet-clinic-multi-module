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
@AllArgsConstructor
@Builder
@Entity
public class Speciality extends BaseEntity{
    @Column(name = "description")
    private String description;

}