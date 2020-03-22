package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    /**
     *
     */
    private static final String LAST_NAME = "LAST_NAME";

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
        
        verifyZeroInteractions(ownerService);
    }
    @Test
    void findAllByLastNameLikeOneResult() throws Exception{
        //Given
        List<Owner> ownersToFind = new ArrayList<>();
        ownersToFind.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
        when(ownerService.findAllByLastNameLike(LAST_NAME)).thenReturn(ownersToFind);
        //When
        mockMvc.perform(get("/owners/findByLastName").param("lastName", LAST_NAME))
                .andExpect(status().is3xxRedirection())//Then
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
        
        verify(ownerService).findAllByLastNameLike(LAST_NAME);
    }
    @Test
    void findAllByLastNameLikeManyResult() throws Exception{
        //Given
        List<Owner> ownersToFind = new ArrayList<>();
        
        ownersToFind.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
        ownersToFind.add(Owner.builder().id(2L).lastName(LAST_NAME).build());
        when(ownerService.findAllByLastNameLike(LAST_NAME)).thenReturn(ownersToFind);
        
        //When
        mockMvc.perform(get("/owners/findByLastName").param("lastName", LAST_NAME))
                .andExpect(status().isOk())//Then
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("selections"))
                .andExpect(model().size(2));
        
        verify(ownerService).findAllByLastNameLike(LAST_NAME);
    }
    @Test
    void createOwnerForm() throws Exception{
        //Given 
        //performing get
        
        //When  
        mockMvc.perform(get("/owners/create"))
                .andExpect(status().isOk())//Then
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
        
        verifyZeroInteractions(ownerService);
    }
    @Test
    void createOwner() throws Exception{
        //Given
        Owner ownerToSave = Owner.builder().id(1L).lastName(LAST_NAME).build(); 
        when(ownerService.save(isA(Owner.class))).thenReturn(ownerToSave);

        //When
        MockHttpServletRequestBuilder req = post("/owners/create")
                    .content("lastName="+ownerToSave.getLastName())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);  
        mockMvc.perform(req)
                .andExpect(status().is3xxRedirection())//Then
                .andExpect(view().name("redirect:/owners/"+ownerToSave.getId()))
                .andExpect(model().attributeExists("owner"));
        
        verify(ownerService).save(isA(Owner.class));
    }
    @Test
    public void findOwnerById() throws Exception{
        //Given
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(owner.getId())).thenReturn(owner);
        //When
        mockMvc.perform(get("/owners/" + owner.getId())) 
                .andExpect(status().isOk())//Then
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).findById(owner.getId());
    }
    @Test
    public void findOwnerByIdNotFound() throws Exception{
        //Given
        when(ownerService.findById(anyLong())).thenReturn(null);
        //When
        mockMvc.perform(get("/owners/2")) 
                .andExpect(status().is3xxRedirection())//Then
                .andExpect(view().name("redirect:/owners/index"));

        verify(ownerService).findById(anyLong());
    }
    @Test
    public void updateOwnerFormTest() throws Exception{
        //Given
        Owner ownerToUpdate = Owner.builder().id(1L).build();
        when(ownerService.findById(ownerToUpdate.getId())).thenReturn(ownerToUpdate);
        
        //When
        mockMvc.perform(get("/owners/" + ownerToUpdate.getId() + "/edit"))
                    .andExpect(status().isOk()) //Then
                    .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                    .andExpect(model().attributeExists("owner"));
        
        verify(ownerService).findById(ownerToUpdate.getId());
    }
    @Test
    public void updateOwnerTest() throws Exception{
        //Given
        Owner owner = Owner.builder().id(1L).build();
        Owner updatedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerService.save(any(Owner.class))).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 1)
                    return updatedOwner;

                return owner;
            }
        });
        /*Insert a owner object to test
            update functionality in controller
         */
        Owner savedOwner = ownerService.save(owner);
        when(ownerService.findById(owner.getId())).thenReturn(savedOwner);
        
        //When 
        mockMvc.perform(post("/owners/" + savedOwner.getId() + "/edit")
                                .content("lastName="+LAST_NAME)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                        .andExpect(status().is3xxRedirection()) //Then
                        .andExpect(view().name("redirect:/owners/" + updatedOwner.getId()))
                        .andExpect(model().attributeExists("owner"));
        
        verify(ownerService).findById(owner.getId());
        verify(ownerService, times(2)).save(isA(Owner.class));
    }
    @Test
    public void updateOwnerNotFoundTest() throws Exception{
        //Given
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.save(any(Owner.class))).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 1)
                    return null;

                return owner;
            }
        });
        /*Insert a owner object to test
            update functionality in controller
         */
        ownerService.save(owner);
        when(ownerService.findById(anyLong())).thenReturn(null);
        
        //When 
        mockMvc.perform(post("/owners/100/edit")
                                .content("lastName="+LAST_NAME)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                        .andExpect(status().isOk()) //Then
                        .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                        .andExpect(model().attributeExists("owner"));
        
        verify(ownerService).findById(anyLong());
        verify(ownerService, times(1)).save(isA(Owner.class));    
    }    
}