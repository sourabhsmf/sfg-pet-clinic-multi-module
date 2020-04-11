package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;


@Controller
@RequestMapping("/pets/{petId}/visits/")
public class VisitController{
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService){
        this.visitService = visitService;
        this.petService = petService;
   }
//    @ModelAttribute("owner")
//    public Owner findOwner(@PathVariable Long ownerId){
//         Owner owner = ownerService.findById(ownerId);
//         if(owner != null ){
//             return owner;
//         }
//         return null;
//    }

    @GetMapping({"", "/index.html", "/index"})
    public String index(Model model , @PathVariable Long petId){
        model.addAttribute("pet", petService.findById(petId));
        return "visits/visitList";
    }
}