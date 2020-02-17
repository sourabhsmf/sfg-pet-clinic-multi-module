package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{

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
        return super.save(owner.getId() , owner);
    }
       
}