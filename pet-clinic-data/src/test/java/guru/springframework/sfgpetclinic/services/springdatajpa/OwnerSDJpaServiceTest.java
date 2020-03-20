package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Sups";

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        //Given
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(2L).build());
        ownerSet.add(Owner.builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        //When
        Set<Owner> owners = ownerSDJpaService.findAll();

        //Then
        assertNotNull(owners);
        assertEquals(2, owners.size());
        assertEquals(ownerSet, owners);
        
    }

    @Test
    void findById() {
        //Given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        
        //When
        Owner returnedOwner = ownerSDJpaService.findById(1L);

        //Then
        assertNotNull(returnedOwner);
        assertEquals(owner, returnedOwner);
    }

    @Test
    void save() {
        //Given
        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(ownerToSave)).thenReturn(owner);

        //When
        Owner savedOwner = ownerSDJpaService.save(ownerToSave);
        
        //Then
        assertEquals(owner, savedOwner);
        verify(ownerRepository).save(ownerToSave);
    }

    @Test
    void delete() {
        //Given
        //owner object to be deleted

        //When
        ownerSDJpaService.delete(owner);
        
        //Then
        verify(ownerRepository, times(1)).delete(owner);
    }

    @Test
    void deleteById() {
        //Given
        //owner object to be deleted

        //When
        ownerSDJpaService.deleteById(owner.getId());
        
        //Then
        verify(ownerRepository, times(1)).deleteById(owner.getId());
    }

    @Test
    void findByLastName() {
        //Given
        List<Owner> owners = new ArrayList<>();
        owners.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
        owners.add(Owner.builder().id(2L).lastName(LAST_NAME).build());

        when(ownerRepository.findAllByLastNameLike(anyString())).thenReturn(owners);
        
        //When
        List<Owner> returnedOwners = ownerSDJpaService.findAllByLastNameLike(LAST_NAME);
        
        //Then
        assertEquals(owners.size(), returnedOwners.size());
        verify(ownerRepository, times(1)).findAllByLastNameLike(LAST_NAME);
    }
}