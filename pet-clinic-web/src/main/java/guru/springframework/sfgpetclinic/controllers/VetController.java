package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.VetService;

@RequestMapping({"/vets" , "/vets.html"})
@Controller
public class VetController{
    private final VetService vetService;

    public VetController(VetService vetService){
        this.vetService = vetService;
    }
    @RequestMapping({"", "/index.html", "/index"})
    public String index(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}