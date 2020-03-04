package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
@Profile({"map","default"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService , PetService petService){
        this.petTypeService = petTypeService;
        this.petService = petService;
    }
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }
    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }
    @Override
    public Owner findByLastName(String lastName) {
        for(Owner owner : map.values()){
            if(owner.getLastName() == lastName){
                return owner;
            }
        }
        return map.get(0L);
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public void delete(Owner owner) {
        super.delete(owner);;
    }
    @Override
    public Owner save(Owner owner) {
        if(owner != null && owner.getPets() != null){
            owner.getPets().forEach(pet -> {
                if(pet.getPetType() != null) {
                    if(pet.getPetType().getId() == null){
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    } 
                }else{
                    throw new RuntimeException("Pet type is required");
                }
                if(pet.getId() == null){
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                } 
            });
        }    
        return super.save(owner);
    }
       
}