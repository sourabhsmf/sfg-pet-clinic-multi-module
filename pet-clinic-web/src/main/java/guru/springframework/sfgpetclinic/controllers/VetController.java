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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.exceptions.VetNotFoundException;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;

@RequestMapping({"/vets" , "/vets.html"})
@Controller
public class VetController{
    private final VetService vetService;
    private final SpecialityService specialityService;
    private static final String VETS_CREATE_OR_UPDATE_VET_FORM = "vets/createOrUpdateVetForm";

    public VetController(VetService vetService, SpecialityService specialityService){
        this.vetService = vetService;
        this.specialityService = specialityService;
    }
    
    @InitBinder(value = {"vet"})
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        //vet
        binder.registerCustomEditor(Date.class, "yearsOfPractice",
                                    new CustomDateEditor(dateFormatter, true));
    }
    @RequestMapping({"", "/index.html", "/index"})
    public String index(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
    //CRUD operation - Create
    @GetMapping({"/create", "/add" , "/new"})
    public String createVetForm(Model model){
        model.addAttribute("vet", Vet.builder().build());
        model.addAttribute("specialities", specialityService.findAll());
        return VETS_CREATE_OR_UPDATE_VET_FORM;
    }
    @PostMapping({"/create", "/add", "/new"})
    public String createVet(@Valid Vet vet, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("specialities", specialityService.findAll());
            return VETS_CREATE_OR_UPDATE_VET_FORM;
        }
        Vet savedVet = vetService.save(vet);
        return "redirect:/vets/" + savedVet.getId();
    }
     //CRUD operation - Read
     @GetMapping("/{vetId}")
     public String findVetById(@PathVariable Long vetId , Model model){
         Vet vet = vetService.findById(vetId);
         if(vet != null){
             model.addAttribute("vet", vet);
             return "vets/vetDetails";
         }
        throw new VetNotFoundException(vetId);     
    }
    //CRUD operation - Update
    @GetMapping({"/{vetId}/edit"})
    public String updateVetForm(@PathVariable Long vetId, Model model){
        Vet vetToUpdate = vetService.findById(vetId);
        model.addAttribute("specialities", specialityService.findAll());
        if(vetToUpdate != null){
            model.addAttribute("vet", vetToUpdate);
        }else{
            model.addAttribute("vet", Vet.builder().build());
        }
        return VETS_CREATE_OR_UPDATE_VET_FORM;
    }

    @PostMapping({"/{vetId}/edit"})
    public String updateVet(@PathVariable Long vetId, @Valid Vet vet,
                                BindingResult result, Model model){
        if(result.hasErrors() || vetService.findById(vetId) == null){
            model.addAttribute("vet", vet);
            model.addAttribute("specialities", specialityService.findAll());
            return VETS_CREATE_OR_UPDATE_VET_FORM;
        }else{
            vet.setId(vetId);
            Vet updatedVet = vetService.save(vet);
            return "redirect:/vets/" + updatedVet.getId();
        }
    }

    //CRUD operation - Delete
    @GetMapping({"/{vetId}/delete", "/{vetId}/remove"})
    public String getMethodName(@PathVariable Long vetId) {
        Vet vetToDelete = vetService.findById(vetId);
        if(vetToDelete != null){
            vetService.delete(vetToDelete);
        }
        return "redirect:/vets/index";
    }

    //Other operations
    //READ
    @GetMapping({"/{vetId}/visits"})
    public String getVisits(@PathVariable Long vetId, Model model) {
        Vet vet = vetService.findById(vetId);
        if(vet != null){
            model.addAttribute("visits", vet.getVisits());
            return "visits/visitList";
        }
        throw new VetNotFoundException(vetId);
    }
        
}