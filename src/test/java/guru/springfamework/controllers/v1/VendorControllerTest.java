package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.ResourceNotFoundException;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    public static final String NAME = "tasty";
    MockMvc mockMvc;

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void getAllVendors() throws Exception {
        //given
        List<VendorDTO> vendorDTOList = Arrays.asList(new VendorDTO(), new VendorDTO(), new VendorDTO());

        when(vendorService.getAllVendors()).thenReturn(vendorDTOList);

        //then
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));
    }

    @Test
    public void findByName() throws Exception {
        //given
        VendorDTO vendorDTO = vendorDTOBuilder();

        when(vendorService.findByName(NAME)).thenReturn(Optional.of(vendorDTO));

        //then
        mockMvc.perform(get(VendorController.BASE_URL + "/name/tasty")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO.getName())));
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendorDTO = vendorDTOBuilder();

        VendorDTO returnVendorDTO = vendorDTO;

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnVendorDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        //given
        VendorDTO vendorDTO = vendorDTOBuilder();
        VendorDTO returnVendorDTO = vendorDTO;

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnVendorDTO);

        //then
        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("vendorUrl", equalTo(VendorController.BASE_URL + "/1")));
    }

    /*@Test
    public void testGetByLastNameNotFound() throws Exception {
        when(vendorService.(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(customerController.BASE_URL + "/Error")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }*/

    private static VendorDTO vendorDTOBuilder() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");
        return vendorDTO;
    }
}