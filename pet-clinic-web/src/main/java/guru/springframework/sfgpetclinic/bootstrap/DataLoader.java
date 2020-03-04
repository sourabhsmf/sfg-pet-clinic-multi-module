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
        PetType dragon = new PetType("Dragon");
        // dragon.setName("Dragon");
        PetType savedDragonPetType = petTypeService.save(dragon);
        
        PetType gobblin = new PetType("Gobblin");
        // gobblin.setName("Gobblin");
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
        warlockPet.setOwner(owner1);
        warlockPet.setDob(new Date(100L));

        
        Visit warlockPetVisit = new Visit();
        warlockPetVisit.setDate(new Date(100L));
        warlockPetVisit.setDescription("Violations");
        warlockPetVisit.setPet(warlockPet);

        visitService.save(warlockPetVisit);
        warlockPet.getVisits().add(warlockPetVisit);

        owner1.getPets().add(warlockPet);

        ownerService.save(owner1);

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
        elfPet.setOwner(owner2);
        elfPet.setDob(new Date(100L));
        
        owner2.getPets().add(elfPet);

        ownerService.save(owner2);

        Visit elfPetVisit = new Visit();
        elfPetVisit.setDate(new Date(100L));
        elfPetVisit.setDescription("Violations");
        elfPetVisit.setPet(elfPet);

        visitService.save(elfPetVisit);
        elfPet.getVisits().add(elfPetVisit);

        System.out.println("Owners have been loaded succcessfully");

        Vet vet1 = new Vet();
        vet1.setYearsOfPractice(new Date(10L));
        vet1.setFirstName("Wizard");
        vet1.setLastName("White");

        //speciality object
        Speciality elixirSpeciality = new Speciality();
        elixirSpeciality.setDescription("Healing Potions");
        specialityService.save(elixirSpeciality);
        
        Speciality enchantedSpeciality = new Speciality();
        enchantedSpeciality.setDescription("Enchanted Potions");
        specialityService.save(enchantedSpeciality);

        vet1.getSpecialities().add(elixirSpeciality);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setYearsOfPractice(new Date(20L));
        vet2.setFirstName("Ogre");
        vet2.setLastName("Old");
        
        vet2.getSpecialities().add(enchantedSpeciality);
        vetService.save(vet2);

        System.out.println("Vets have been loaded succcessfully");
        
    }

}