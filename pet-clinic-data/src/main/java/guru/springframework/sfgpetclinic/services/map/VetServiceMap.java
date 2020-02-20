package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService{

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }
    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public void delete(Vet Vet) {
        super.delete(Vet);;
    }
    @Override
    public Vet save(Vet Vet) {
        return super.save(Vet.getId() , Vet);
    }
       
}