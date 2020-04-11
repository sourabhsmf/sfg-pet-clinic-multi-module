package guru.springframework.sfgpetclinic.exceptions;


public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(Long id) {

        super(String.format("Owner with Id %d not found", id));
    }
}