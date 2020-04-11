package guru.springframework.sfgpetclinic.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {  
    @ExceptionHandler(OwnerNotFoundException.class)
    public String handleOwnerNotFoundException(OwnerNotFoundException ex, WebRequest request, Model model){
        model.addAttribute("ex", ex);
        return "404";
    }
    @ExceptionHandler(PetNotFoundException.class)
    public String handlePetNotFoundException(PetNotFoundException ex, WebRequest request, Model model){
        model.addAttribute("ex", ex);
        return "404";
    }
    @ExceptionHandler(OwnerPetRelationException.class)
    public String handleOwnerPetRelationException(OwnerPetRelationException ex, WebRequest request, Model model){
        model.addAttribute("ex", ex);
        return "403";
    }
}