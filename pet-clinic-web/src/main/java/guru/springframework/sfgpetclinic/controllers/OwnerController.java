package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import javax.validation.Valid;


@RequestMapping({"/owners" , "owners.html"})
@Controller
public class OwnerController{
    
    /**
     *
     */
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/index.html", "/index"})
    public String index(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    //Find operation
    @GetMapping({"/find"})
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({"/findByLastName"})
    public String findAllByLastNameLike(Owner owner, BindingResult result, Model model){
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName());
        if(owners.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if(owners.size() == 1){
            owner = owners.get(0);
            return "redirect:/owners/" + owner.getId();
        }else{
            model.addAttribute("selections" , owners);
            return "owners/ownersList";
        }
    }

    //CRUD operation - Create
    @GetMapping({"/create", "/add" , "/new"})
    public String createOwnerForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping({"/create", "/add", "/new"})
    public String createOwner(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()){
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    //CRUD operation - Read
    @GetMapping("/{ownerId}")
    public String findOwnerById(@PathVariable Long ownerId , Model model){
        Owner owner = ownerService.findById(ownerId);
        if(owner != null){
            model.addAttribute("owner", owner);
            return "owners/ownerDetails";
        }
        return "redirect:/owners/index";
    }

    //CRUD operation - Update
    @GetMapping({"/{ownerId}/edit"})
    public String updateOwnerForm(@PathVariable Long ownerId, Model model){
        Owner ownerToUpdate = ownerService.findById(ownerId);
        if(ownerToUpdate != null){
            model.addAttribute("owner", ownerToUpdate);
        }else{
            model.addAttribute("owner", Owner.builder().build());
        }
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping({"/{ownerId}/edit"})
    public String updateOwner(@PathVariable Long ownerId, @Valid Owner owner,
                                BindingResult result, Model model){
        if(result.hasErrors() || ownerService.findById(ownerId) == null){
            model.addAttribute("owner", owner);
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }else{
            owner.setId(ownerId);
            Owner updatedOwner = ownerService.save(owner);
            return "redirect:/owners/" + updatedOwner.getId();
        }
    }

    //CRUD operation - Delete
    @GetMapping({"/{ownerId}/delete", "/{ownerId}/remove"})
    public String getMethodName(@PathVariable Long ownerId) {
        Owner ownerToDelete = ownerService.findById(ownerId);
        if(ownerToDelete != null){
            ownerService.delete(ownerToDelete);
        }
        return "redirect:/owners/index";
    }
}