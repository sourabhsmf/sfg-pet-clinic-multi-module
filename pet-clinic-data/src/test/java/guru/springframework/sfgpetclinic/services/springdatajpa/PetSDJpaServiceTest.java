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
        //When
        Set<Pet> petSet = new HashSet<>();
        petSet.add(Pet.builder().Id(2L).build());
        petSet.add(Pet.builder().Id(3L).build());

        when(petRepository.findAll()).thenReturn(petSet);

        //When
        Set<Pet> pets = petSDJpaService.findAll();

        //Then
        assertNotNull(pets);
        assertEquals(2, pets.size());
        assertEquals(petSet, pets);
        verify(petRepository).findAll();
    }
    @Test
    void findById(){
        //Given
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));

        //When
        Pet returnedPet = petSDJpaService.findById(pet.getId());
        
        //Then
        assertEquals(pet, returnedPet);
        verify(petRepository).findById(pet.getId());
    }
    @Test
    void save(){
        //Given
        Pet petToSave = Pet.builder().Id(1L).build();
        when(petRepository.save(petToSave)).thenReturn(pet);

        //When
        Pet savedPet = petSDJpaService.save(petToSave);
        
        //Then
        assertEquals(pet, savedPet);
        verify(petRepository).save(petToSave);
    }
}