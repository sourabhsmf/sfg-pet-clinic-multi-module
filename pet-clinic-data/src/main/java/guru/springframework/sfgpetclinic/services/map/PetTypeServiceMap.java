package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType , Long> implements PetTypeService{
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }
    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public void delete(PetType PetType) {
        super.delete(PetType);;
    }
    @Override
    public PetType save(PetType PetType) {
        return super.save(PetType);
    }
    
}