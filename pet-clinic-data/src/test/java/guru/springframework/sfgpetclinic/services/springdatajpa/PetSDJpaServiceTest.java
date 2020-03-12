package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petSDJpaService;

    Pet pet;

    @BeforeEach
    void setUp(){
        pet = Pet.builder().Id(1L).build();
    }

    @Test
    void findAll(){
        Set<Pet> petSet = new HashSet<>();
        petSet.add(Pet.builder().Id(2L).build());
        petSet.add(Pet.builder().Id(3L).build());

        when(petRepository.findAll()).thenReturn(petSet);

        assertNotNull(petSet);
        assertEquals(2, petSDJpaService.findAll().size());

        verify(petRepository, times(1)).findAll();
    }
    @Test
    void findById(){
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));

        assertEquals(pet, petSDJpaService.findById(pet.getId()));

        verify(petRepository).findById(anyLong());
    }
    @Test
    void save(){
        when(petRepository.save(any())).thenReturn(any(Pet.class));

        pet.setOwner(Owner.builder().id(1L).build());

        assertEquals(pet, petSDJpaService.save(pet));

        verify(petRepository).save(any());
    }
}