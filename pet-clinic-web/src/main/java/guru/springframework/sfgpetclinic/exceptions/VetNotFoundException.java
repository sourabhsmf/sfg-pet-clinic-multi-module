package guru.springframework.sfgpetclinic.exceptions;

public class VetNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 7012099802386329880L;

    public VetNotFoundException(Long id) {

        super(String.format("Vet with Id %d not found", id));
    }

}