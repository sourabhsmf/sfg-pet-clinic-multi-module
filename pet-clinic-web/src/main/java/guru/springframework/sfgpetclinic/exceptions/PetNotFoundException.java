package guru.springframework.sfgpetclinic.exceptions;

public class PetNotFoundException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = -4643939905772994178L;

    public PetNotFoundException(Long id) {

        super(String.format("Pet with Id %d not found", id));
    }
}