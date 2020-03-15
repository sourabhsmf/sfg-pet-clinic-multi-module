package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;

    PetType petType;

    @BeforeEach
    void setUp() {
        petType = PetType.builder().Id(1L).build();
    }

    @Test
    void findAll() {
        //Given
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(PetType.builder().Id(2L).build());
        petTypeSet.add(PetType.builder().Id(3L).build());
        when(petTypeRepository.findAll()).thenReturn(petTypeSet);

        //When
        Set<PetType> petTypSetReturned = petTypeSDJpaService.findAll();

        //Then
        assertEquals(2, petTypSetReturned.size());
        assertEquals(petTypeSet, petTypSetReturned);
        verify(petTypeRepository).findAll();
    }

    @Test
    void findById() {
        //Given
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(petType));

        //When
        PetType petTypeReturned = petTypeSDJpaService.findById(petType.getId());

        //Then
        assertEquals(petType, petTypeReturned);
        verify(petTypeRepository).findById(petType.getId());
    }

    @Test
    void save() {
        //Given
        PetType petTypeToSave = PetType.builder().Id(1L).build();
        when(petTypeRepository.save(petTypeToSave)).thenReturn(petType);

        //When
        PetType petTypeSaved = petTypeSDJpaService.save(petTypeToSave);

        //Then
        assertEquals(petType, petTypeSaved);
        verify(petTypeRepository).save(petTypeToSave);
    }

    @Test
    void delete() {
        //Given
        //petType object will be deleted

        //When
        petTypeSDJpaService.delete(petType);

        //Then
        verify(petTypeRepository).delete(petType);
    }

    @Test
    void deleteById() {
        //Given
        //petType object will be deleted

        //When
        petTypeSDJpaService.deleteById(petType.getId());

        //Then
        verify(petTypeRepository).deleteById(petType.getId());
    }
}