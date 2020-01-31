package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;
import guru.springfamework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                //have to set this so all controller will know about ControllerAdvice
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //arrange
        CustomerDTO customerDTO1 = new CustomerDTO();
        CustomerDTO customerDTO2 = new CustomerDTO();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(customerDTO1);
        customerDTOS.add(customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.getBaseUrl()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOS", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        //arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Zach");
        customerDTO.setLastName("Marbach");
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.getBaseUrl() + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Zach")))
                .andExpect(jsonPath("$.lastName", equalTo("Marbach")));
    }

    @Test
    public void postNewCustomer() throws Exception {
        //arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Kelli");
        customerDTO.setLastName("Marbach");
        customerDTO.setCustomer_url(CustomerController.getBaseUrl() + "/1");
        when(customerService.createNewCustomer(ArgumentMatchers.any())).thenReturn(customerDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.getBaseUrl() + "/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Kelli")))
                .andExpect(jsonPath("$.lastName", equalTo("Marbach")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        //arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Zach");
        customerDTO.setLastName("Marbach");
        customerDTO.setCustomer_url(CustomerController.getBaseUrl() + "/1");

        when(customerService.patchCustomer(anyLong(), ArgumentMatchers.any())).thenReturn(customerDTO);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.patch(CustomerController.getBaseUrl() + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Zach")))
                .andExpect(jsonPath("$.lastName", equalTo("Marbach")));
    }

    @Test
    public void testDeleteCustomerById() throws Exception {
        //arrange

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.delete(CustomerController.getBaseUrl() + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }

    @Test
    public void testBadUrlReturnsNotFoundException() throws Exception {
        //remember to setControllerAdvice in setup

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.getBaseUrl() + "/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}