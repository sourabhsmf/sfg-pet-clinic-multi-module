package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository){
        this.petRepository = petRepository;
    }
    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pet -> pets.add(pet));
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

}