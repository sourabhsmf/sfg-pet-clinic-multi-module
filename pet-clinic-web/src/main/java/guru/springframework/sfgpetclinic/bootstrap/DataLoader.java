package guru.springframework.sfgpetclinic.bootstrap;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService,VetService vetService){
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception{
        Owner owner1 = new Owner(21);
        owner1.setFirstName("Warlock");
        owner1.setLastName("Mortal");
        
        ownerService.save(owner1);

        Owner owner2 = new Owner(22);
        owner2.setFirstName("Elf");
        owner2.setLastName("Immune");
        
        ownerService.save(owner2);

        System.out.println("Owners have been loaded succcessfully");

        Vet vet1 = new Vet(new Date(10L));
        vet1.setFirstName("Wizard");
        vet1.setLastName("White");
        
        vetService.save(vet1);

        Vet vet2 = new Vet(new Date(20L));
        vet2.setFirstName("Ogre");
        vet2.setLastName("Old");
        
        vetService.save(vet2);

        System.out.println("Vets have been loaded succcessfully");
        
    }

}