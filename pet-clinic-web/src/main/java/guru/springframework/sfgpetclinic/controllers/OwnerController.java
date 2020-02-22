package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.OwnerService;

@Controller
public class OwnerController{
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners", "/owners/index.html", "/owners/index"})
    public String index(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}