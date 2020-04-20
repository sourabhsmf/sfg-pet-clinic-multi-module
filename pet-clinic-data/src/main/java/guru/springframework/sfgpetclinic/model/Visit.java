package guru.springframework.sfgpetclinic.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "date")
    @NotNull(message = "Date of Visit cannot be blank")
    private Date date;

    @Column(name = "description")
    @NotBlank(message = "Pet name cannot be blank")
    @Pattern(regexp = "([A-Z a-z])+", message = "Visit description must contain letters only")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Builder
    public Visit(Long Id, Date date, String description, Pet pet) {
        super(Id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }
}