package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
 public class Person extends BaseEntity {
    @Column(name = "first_name")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    public Person(Long Id, String firstName, String lastName) {
        super(Id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
