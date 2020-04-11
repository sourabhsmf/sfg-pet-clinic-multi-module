package guru.springframework.sfgpetclinic.exceptions;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;

public class OwnerPetRelationException extends RuntimeException{
    public OwnerPetRelationException(Owner owner , Pet pet) {

        super(String.format("Owner %s does not have access to pet %s", 
                            (owner.getFirstName() + " " + owner.getLastName()),
                            (pet.getPetName())));
    }
}