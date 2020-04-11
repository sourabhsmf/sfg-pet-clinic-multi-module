package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.exceptions.OwnerNotFoundException;
import guru.springframework.sfgpetclinic.exceptions.OwnerPetRelationException;
import guru.springframework.sfgpetclinic.exceptions.PetNotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;


@RequestMapping({ "/owners/{ownerId}/pets", "/owners/{ownerId}/pets.html" })
@Controller
public class PetController{
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService){
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
   }
   @ModelAttribute
   public void init(@PathVariable Long ownerId, @PathVariable(required = false) Long petId, 
                            Model model){
        Owner owner = ownerService.findById(ownerId);
        if(owner == null){
            throw new OwnerNotFoundException(ownerId);
        }else if(petId == null){
            model.addAttribute("owner", owner);
        }else{
            Pet pet = petService.findById(petId);
            if(pet == null){
                throw new PetNotFoundException(petId);
            }else if(!pet.getOwner().equals(owner)){
                throw new OwnerPetRelationException(owner, pet);
            }else{
                model.addAttribute("owner", owner);
                model.addAttribute("pet", pet);
            }
        }
   }
    @GetMapping({"", "/index.html", "/index"})
    public String index(Model model){
        if(model.asMap().get("owner") != null){
            return "pets/petsList";
        }
        
        return "owners/index";
    }
    //CRUD OPERATIONS
    //CREATE
    @GetMapping({"/add", "/create", "/new"})
    public String createPetForm(Model model){
        
        Pet petToSave = Pet.builder().build();
        
        model.addAttribute("pet", petToSave);
        model.addAttribute("types", petTypeService.findAll());
        
        return "pets/createOrUpdatePetForm";
    
    }
    @PostMapping({"/add", "/create", "/new"})
    public String createPet(Model model, Pet pet, BindingResult bindingResult){
        Owner owner = (Owner)model.asMap().get("owner");
    
        if(!bindingResult.hasErrors()){
            pet.setOwner(owner);
            Pet petSaved = petService.save(pet);
            return "redirect:/owners/" + owner.getId() + "/pets/" + petSaved.getId();
        }
        
        model.addAttribute("pet", pet);
        
        return "redirect:";
    }
    //READ
    @GetMapping({"/{petId}"})
    public String getPet(@PathVariable Long petId, Model model) {
        return "pets/petDetails";
    }
    //UPDATE
    @GetMapping({"/{petId}/edit"})
    public String updateFormTest(@PathVariable Long petId, Model model){
        model.addAttribute("types", petTypeService.findAll());
        
        return "pets/createOrUpdatePetForm";
    }
    @PostMapping({"/{petId}/edit"})
    public String updatePet(@PathVariable Long petId, Pet updatedPet, Model model, BindingResult result){
        Owner owner = (Owner)model.asMap().get("owner");
        
        if(!result.hasErrors()){
            updatedPet.setOwner(owner);
            petService.save(updatedPet);
            return "redirect:";
        }

        model.addAttribute("pet", updatedPet);
        
        return "redirect:"+ petId +"edit/";
    }
    //DELETE
    @GetMapping({"/{petId}/delete"})
    public String deletePet(@PathVariable Long petId, Model model){
        Owner owner = (Owner)model.asMap().get("owner");
        Pet petToDelete = (Pet)model.asMap().get("pet");

        petService.delete(petToDelete);
        
        return "redirect:/owners/" + owner.getId() + "/pets";
        
    }
}