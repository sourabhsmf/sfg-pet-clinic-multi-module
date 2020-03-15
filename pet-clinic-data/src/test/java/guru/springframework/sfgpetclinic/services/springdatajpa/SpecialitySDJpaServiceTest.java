package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
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
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    Speciality speciality;

    @BeforeEach
    void setUp() {
        speciality = Speciality.builder().Id(1L).build();
    }

    @Test
    void findAll() {
        //Given
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(Speciality.builder().Id(1L).build());
        specialitySet.add(Speciality.builder().Id(2L).build());
        when(specialityRepository.findAll()).thenReturn(specialitySet);

        //When
        Set<Speciality> specialitySetReturned = specialitySDJpaService.findAll();

        //Then
        assertEquals(2, specialitySetReturned.size());
        assertEquals(specialitySet, specialitySetReturned);
        verify(specialityRepository).findAll();

    }

    @Test
    void findById() {
        //Given
        when(specialityRepository.findById(speciality.getId())).thenReturn(Optional.of(speciality));

        //When
        Speciality specialityReturned = specialitySDJpaService.findById(speciality.getId());

        //Then
        assertEquals(speciality, specialityReturned);
        verify(specialityRepository).findById(speciality.getId());
    }

    @Test
    void save() {
        //Given
        Speciality specialityToSave = Speciality.builder().Id(1L).build();
        when(specialityRepository.save(specialityToSave)).thenReturn(speciality);

        //When
        Speciality specialitySaved = specialitySDJpaService.save(specialityToSave);

        //Then
        assertEquals(speciality, specialitySaved);
        verify(specialityRepository).save(specialityToSave);

    }

    @Test
    void delete() {
        //Given
        //speciality object will be deleted

        //When
        specialitySDJpaService.delete(speciality);

        //Then
        verify(specialityRepository).delete(speciality);
    }

    @Test
    void deleteById() {
        //Given
        //speciality object will be deleted

        //When
        specialitySDJpaService.deleteById(speciality.getId());

        //Then
        verify(specialityRepository).deleteById(speciality.getId());


    }
}