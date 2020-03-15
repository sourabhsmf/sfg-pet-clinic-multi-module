package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetSDJpaService;

    Vet vet;

    @BeforeEach
    void setUp() {
        vet = Vet.builder().Id(1L).build();
    }

    @Test
    void findAll() {
        //Given
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(Vet.builder().Id(2L).build());
        vetSet.add(Vet.builder().Id(3L).build());
        when(vetRepository.findAll()).thenReturn(vetSet);

        //When
        Set<Vet> vetSetReturned = vetSDJpaService.findAll();

        //Then
        assertEquals(2, vetSetReturned.size());
        assertEquals(vetSet , vetSetReturned);
        verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        //Given
        when(vetRepository.findById(vet.getId())).thenReturn(Optional.of(vet));

        //When
        Vet vetReturned = vetSDJpaService.findById(vet.getId());

        //Then
        assertEquals(vet , vetReturned);
        verify(vetRepository).findById(vet.getId());
    }

    @Test
    void save() {
        //Given
        Vet vetToSave = Vet.builder().Id(1L).build();
        when(vetRepository.save(vetToSave)).thenReturn(vet);

        //When
        Vet vetSaved = vetSDJpaService.save(vetToSave);

        //Then
        assertEquals(vet, vetSaved);
        verify(vetRepository).save(vetToSave);
    }

    @Test
    void delete() {
        //Given
        //vet object to be deleted

        //When
        vetSDJpaService.delete(vet);

        //Then
        verify(vetRepository).delete(vet);
    }

    @Test
    void deleteById() {
        //Given
        //vet object to be deleted

        //When
        vetSDJpaService.deleteById(vet.getId());

        //Then
        verify(vetRepository).deleteById(vet.getId());
    }
}