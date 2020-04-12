package guru.springframework.sfgpetclinic.bootstrap;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
@Component
public class DataLoader implements CommandLineRunner{

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    public DataLoader(OwnerService ownerService,VetService vetService,
                    PetTypeService petTypeService, SpecialityService specialityService,
                    VisitService visitService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception{
        if(petTypeService.findAll().size() == 0) { loadData(); }
    }
    
    public void loadData(){    
        //Pet type
        PetType dragon = new PetType();
        dragon.setName("Dragon");
        PetType savedDragonPetType = petTypeService.save(dragon);
        
        PetType gobblin = new PetType();
        gobblin.setName("Gobblin");
        PetType savedGobblinPetType = petTypeService.save(gobblin);
        
        //Owners
        Owner owner1 = new Owner();
        owner1.setAge(21);
        owner1.setFirstName("Warlock");
        owner1.setLastName("Mortal");
        owner1.setAddress("1,Skylake");
        owner1.setCity("Ellis");
        owner1.setTelephone("1222111111");

        //owner1 pet object
        Pet warlockPet = new Pet();
        warlockPet.setPetName("Dragon");
        warlockPet.setDisease("Fire Breath");
        warlockPet.setPetType(savedDragonPetType);
        warlockPet.setDob(new Date(100L));
        warlockPet.setOwner(owner1);
        
        //Required for map based service impl
        owner1.getPets().add(warlockPet);

        //Get the persistent owner2 object
        owner1 = ownerService.save(owner1);

        Visit warlockPetVisit = new Visit();
        warlockPetVisit.setDate(new Date(100L));
        warlockPetVisit.setDescription("Violations");
        warlockPetVisit.setPet(owner1.getPets().iterator().next());
        
        warlockPetVisit = visitService.save(warlockPetVisit);
        warlockPet.getVisits().add(warlockPetVisit);

        Owner owner2 = new Owner();
        owner2.setAge(22);
        owner2.setFirstName("Elf");
        owner2.setLastName("Immune");
        owner2.setAddress("15,Killian");
        owner2.setCity("Titan");
        owner2.setTelephone("1222111111");
        
        //owner2 pet object
        Pet elfPet = new Pet();
        elfPet.setPetName("Gobblin");
        elfPet.setDisease("Coin Finger");
        elfPet.setPetType(savedGobblinPetType);
        elfPet.setDob(new Date(100L));
        elfPet.setOwner(owner2);
        
        //Required for map based service impl
        owner2.getPets().add(elfPet);
        //Get the persistent owner2 object
        owner2 = ownerService.save(owner2);
        

        Visit elfPetVisit = new Visit();
        elfPetVisit.setDate(new Date(100L));
        elfPetVisit.setDescription("Violations");
        elfPetVisit.setPet(owner2.getPets().iterator().next());

        elfPetVisit = visitService.save(elfPetVisit);
        elfPet.getVisits().add(elfPetVisit);

        System.out.println("Owners have been loaded succcessfully");

        Vet vet1 = new Vet();
        vet1.setYearsOfPractice(new Date(10L));
        vet1.setFirstName("Wizard");
        vet1.setLastName("White");

        //speciality object
        Speciality elixirSpeciality = new Speciality();
        elixirSpeciality.setDescription("Healing Potions");
        elixirSpeciality = specialityService.save(elixirSpeciality);
        
        Speciality enchantedSpeciality = new Speciality();
        enchantedSpeciality.setDescription("Enchanted Potions");
        enchantedSpeciality = specialityService.save(enchantedSpeciality);

        vet1.getSpecialities().add(elixirSpeciality);
        vet1 = vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setYearsOfPractice(new Date(20L));
        vet2.setFirstName("Ogre");
        vet2.setLastName("Old");
        
        vet2.getSpecialities().add(enchantedSpeciality);
        vet2 = vetService.save(vet2);

        elfPetVisit.setVet(vet2);
        visitService.save(elfPetVisit);
        warlockPetVisit.setVet(vet1);
        visitService.save(warlockPetVisit);
        
        System.out.println("Vets have been loaded succcessfully");
        
    }

}