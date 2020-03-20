package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void index() throws Exception{
        //Given ownerController object

        //When
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk()) //Then
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));
        
        verify(ownerService).findAll();
    }
    @Test
    void findOwners() throws Exception{
        //Given ownerController object

        //When
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk()) //Then
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
    }
    void findOwnersByLastName() throws Exception{
        //Given
        Owner ownerToFind = Owner.builder().lastName("Sinha").build();
        //When
        // mockMvc.perform(post())
    }
    
}