package guru.springframework.sfgpetclinic.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<AddressConstraint, String> {



    @Override
    public void initialize(AddressConstraint constraintAnnotation) {
    }
    @Override
    public boolean isValid(String address, ConstraintValidatorContext constraintValidatorContext) {
        String whitelistedCharacters = ",-./\\\n\t";

        //TODO-Remove and replace with @NotNull
        if(address == null || address.length()==0){
            return false;
        }
        for(Character letter : address.toCharArray()){
            if(!Character.isAlphabetic((int)letter) && !Character.isDigit((int)letter)
               && whitelistedCharacters.indexOf(letter.toString()) < 0){
                return false;
            }
        }
        return true;
    }
}
