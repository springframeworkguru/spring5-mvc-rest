package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.customExceptions.ResourceAlreadyExistsException;
import guru.springfamework.customExceptions.ResourceNotFoundException;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Base64;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    VendorListDTO vendorListDTO;
    VendorDTO fakeVendorDTO1;

    private final String BASE_URL = "/api/v1/vendors";
    private final String NAME = "fake vendor";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(vendorController)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();

        vendorListDTO = new VendorListDTO();
        vendorListDTO.setVendors(new ArrayList<>());
        fakeVendorDTO1 = VendorDTO.builder().name(NAME).vendor_url(BASE_URL + "/1").build();
        vendorListDTO.getVendors().add(fakeVendorDTO1);
        vendorListDTO.getVendors().add(VendorDTO.builder().name("Anotha one").build());
    }

    @Test
    public void getAllVendors() throws Exception {
        //arrange
        when(vendorService.getAllVendors()).thenReturn(vendorListDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        //arrange
        when(vendorService.getVendorById(anyLong())).thenReturn(fakeVendorDTO1);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void postNewVendor() throws Exception {
        //arrange
        when(vendorService.createNewVendor(ArgumentMatchers.any())).thenReturn(fakeVendorDTO1);

        //use this to convert Json object to String so can pass to content below
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAsString = objectMapper.writeValueAsString(fakeVendorDTO1);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void postNewVendorNameAlreadyExists() throws Exception {
        //arrange
        VendorDTO vendorDTOWithSameNameAsExistingRecord = new VendorDTO();
        vendorDTOWithSameNameAsExistingRecord.setName(fakeVendorDTO1.getName());
        vendorDTOWithSameNameAsExistingRecord.setVendor_url(fakeVendorDTO1.getVendor_url());
        when(vendorService.createNewVendor(ArgumentMatchers.any())).thenThrow(new ResourceAlreadyExistsException());

        //use this to convert Json object to String so can pass to content below
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAsString = objectMapper.writeValueAsString(vendorDTOWithSameNameAsExistingRecord);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsString))
                .andExpect(status().isConflict());
    }

    @Test
    public void deleteVendorById() throws Exception {
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void putVendor() throws Exception {
        //arrange
        Vendor sampleVendor = new Vendor();
        sampleVendor.setName("sample");
        VendorDTO sampleVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(sampleVendor);
        sampleVendorDTO.setVendor_url(BASE_URL + "/4");
        when(vendorService.saveVendorById(anyLong(),ArgumentMatchers.any())).thenReturn(sampleVendorDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAsString = objectMapper.writeValueAsString(sampleVendorDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(sampleVendorDTO.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        //arrange
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("sample");
        vendorDTO.setVendor_url(BASE_URL + "/1");
        when(vendorService.patchVendor(anyLong(), ArgumentMatchers.any())).thenReturn(vendorDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAsString = objectMapper.writeValueAsString(vendorDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO.getName())));
    }

    @Test
    public void testBadUrlReturnsNotFoundException() throws Exception {
        //remember to setControllerAdvice in setup

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}