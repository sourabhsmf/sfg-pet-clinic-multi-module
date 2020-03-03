package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;

public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    public VetSDJpaService(VetRepository vetRepository){
        this.vetRepository = vetRepository;
    }
    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vet -> vets.add(vet));
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vet save(Vet object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Vet object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

}