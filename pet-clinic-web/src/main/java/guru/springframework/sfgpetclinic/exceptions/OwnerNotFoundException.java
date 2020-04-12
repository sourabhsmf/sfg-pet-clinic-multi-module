package guru.springframework.sfgpetclinic.exceptions;


public class OwnerNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -525551508379442423L;

    public OwnerNotFoundException(Long id) {

        super(String.format("Owner with Id %d not found", id));
    }
}