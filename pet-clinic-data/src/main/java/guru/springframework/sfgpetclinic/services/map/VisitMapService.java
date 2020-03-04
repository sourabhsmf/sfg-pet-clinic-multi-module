package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;

@Service
@Profile({"map","default"})
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService{
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }
    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public void delete(Visit owner) {
        super.delete(owner);;
    }
    @Override
    public Visit save(Visit owner) {
        return super.save(owner);
    }    
}