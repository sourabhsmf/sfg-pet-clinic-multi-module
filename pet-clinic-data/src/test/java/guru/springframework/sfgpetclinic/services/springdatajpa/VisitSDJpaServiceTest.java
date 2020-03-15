package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    Visit visit;

    @BeforeEach
    void setUp() {
        visit = Visit.builder().Id(1L).build();
    }

    @Test
    void findAll() {
        //Given
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(Visit.builder().Id(2L).build());
        visitSet.add(Visit.builder().Id(3L).build());
        when(visitRepository.findAll()).thenReturn(visitSet);

        //When
        Set<Visit> visitSetReturned = visitSDJpaService.findAll();

        //Then
        assertEquals(2, visitSetReturned.size());
        assertEquals(visitSet , visitSetReturned);
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        //Given
        when(visitRepository.findById(visit.getId())).thenReturn(Optional.of(visit));

        //When
        Visit visitReturned = visitSDJpaService.findById(visit.getId());

        //Then
        assertEquals(visit , visitReturned);
        verify(visitRepository).findById(visit.getId());
    }

    @Test
    void save() {
        //Given
        Visit visitToSave = Visit.builder().Id(1L).build();
        when(visitRepository.save(visitToSave)).thenReturn(visit);

        //When
        Visit visitSaved = visitSDJpaService.save(visitToSave);

        //Then
        assertEquals(visit, visitSaved);
        verify(visitRepository).save(visitToSave);
    }

    @Test
    void delete() {
        //Given
        //vet object to be deleted

        //When
        visitSDJpaService.delete(visit);

        //Then
        verify(visitRepository).delete(visit);
    }

    @Test
    void deleteById() {
        //Given
        //vet object to be deleted

        //When
        visitSDJpaService.deleteById(visit.getId());

        //Then
        verify(visitRepository).deleteById(visit.getId());
    }
}