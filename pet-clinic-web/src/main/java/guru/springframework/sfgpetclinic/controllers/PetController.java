package guru.springframework.sfgpetclinic.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.exceptions.OwnerNotFoundException;
import guru.springframework.sfgpetclinic.exceptions.OwnerPetRelationException;
import guru.springframework.sfgpetclinic.exceptions.PetNotFoundException;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VisitService;


@RequestMapping({ "/owners/{ownerId}/pets", "/owners/{ownerId}/pets.html" })
@Controller
public class PetController{
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    
    private final VisitService visitService;

    public PetController(PetService petService, OwnerService ownerService, 
                        PetTypeService petTypeService, VisitService visitService){
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
   }

   @InitBinder(value = {"visit", "pet"})
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        //visit
        binder.registerCustomEditor(Date.class, "date",
                                    new CustomDateEditor(dateFormatter, true));
        //pet
        binder.registerCustomEditor(Date.class, "dob",
                                    new CustomDateEditor(dateFormatter, true));                                 
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
    public String createPet(Model model, @Valid Pet pet, BindingResult bindingResult){
        Owner owner = (Owner)model.asMap().get("owner");
        if(!bindingResult.hasErrors()){
            pet.setOwner(owner);
            Pet petSaved = petService.save(pet);
            return "redirect:/owners/" + owner.getId() + "/pets/" + petSaved.getId();
        }
        
        model.addAttribute("pet", pet);
        model.addAttribute("types", petTypeService.findAll());

        return "pets/createOrUpdatePetForm";
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
    public String updatePet(@PathVariable Long petId, @Valid Pet updatedPet, Model model, 
                            BindingResult result){
        Owner owner = (Owner)model.asMap().get("owner");
        
        if(!result.hasErrors()){
            updatedPet.setOwner(owner);
            petService.save(updatedPet);
            return "redirect:";
        }

        model.addAttribute("pet", updatedPet);
        
        return "pets/createOrUpdatePetForm";
    }
    //DELETE
    @GetMapping({"/{petId}/delete"})
    public String deletePet(@PathVariable Long petId, Model model){
        Owner owner = (Owner)model.asMap().get("owner");
        Pet petToDelete = (Pet)model.asMap().get("pet");

        petService.delete(petToDelete);
        
        return "redirect:/owners/" + owner.getId() + "/pets";
        
    }

    //CREATE NEW VISIT
    @GetMapping({"/{petId}/visits/new"})
    public String createVisitForm(Model model){

        model.addAttribute("visit", Visit.builder().build());
        
        return "pets/createOrUpdateVisitForm";
    
    }
    @PostMapping({"/{petId}/visits/add", "/{petId}/visits/create", "/{petId}/visits/new"})
    public String createVisit(Model model, @Valid Visit visit, BindingResult bindingResult){
        Pet pet = (Pet)model.asMap().get("pet");
        if(!bindingResult.hasErrors()){
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/" + pet.getOwner().getId() 
                    + "/pets/" + pet.getId() + "/visits/";
        }
        
        model.addAttribute("visit", visit);
        
        return "pets/createOrUpdateVisitForm";
    }
    //READ
    @GetMapping({"/{petId}/visits"})
    public String getVisits(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        if(pet != null){
            model.addAttribute("visits", pet.getVisits());
            return "visits/visitList";
        }
        throw new PetNotFoundException(petId);
    }
}