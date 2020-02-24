package guru.springframework.sfgpetclinic.bootstrap;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    public DataLoader(OwnerService ownerService,VetService vetService,PetTypeService petTypeService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception{
        
        //Pet type
        PetType dragon = new PetType("Dragon");
        PetType savedDragonPetType = petTypeService.save(dragon);
        
        PetType gobblin = new PetType("Gobblin");
        PetType savedGobblinPetType = petTypeService.save(gobblin);
        
        //Owners
        Owner owner1 = new Owner(21);
        owner1.setFirstName("Warlock");
        owner1.setLastName("Mortal");
        owner1.setAddress("1,Skylake");
        owner1.setCity("Ellis");
        owner1.setTelephone("1222111111");

        //owner1 pet object
        Pet warlockPet = new Pet("Dragon" , "Fire Breath");
        warlockPet.setPetType(savedDragonPetType);
        warlockPet.setOwner(owner1);
        warlockPet.setDob(new Date(100L));

        owner1.getPets().add(warlockPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner(22);
        owner2.setFirstName("Elf");
        owner2.setLastName("Immune");
        owner2.setAddress("15,Killian");
        owner2.setCity("Titan");
        owner2.setTelephone("1222111111");
        
        //owner2 pet object
        Pet elfPet = new Pet("Gobblin" , "Coin Finger");
        elfPet.setPetType(savedGobblinPetType);
        elfPet.setOwner(owner2);
        elfPet.setDob(new Date(100L));
        
        owner2.getPets().add(elfPet);

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