package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.services.CustomerService;
import guru.springfamework.services.ResourceNotFoundException;
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

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "Paidi";
    public static final String LAST_NAME = "OSe";
    public static final Long ID = 1L;
    public static final String CUSTOMER_URL_BY_ID = "/api/v1/customers/1";

    MockMvc mockMvc;
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(),new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        //then
        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));
    }

    @Test
    public void getCustomerByFirstName() throws Exception {
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerByFirstName(FIRST_NAME)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get(CustomerController.BASE_URL+"/firstname/Paidi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)));

    }

    @Test
    public void getCustomerByLastName() throws Exception {
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerByLastName(LAST_NAME)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get(CustomerController.BASE_URL+"/lastname/OSe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerById(ID)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get(CUSTOMER_URL_BY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);

        CustomerDTO returnDTO = returnCustomerDTOBuilder(customerDTO);

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL_BY_ID)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO);

        //then
        mockMvc.perform(put(CUSTOMER_URL_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL_BY_ID)));

    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = customerDtoBuilder(FIRST_NAME, LAST_NAME);
        CustomerDTO returnCustomerDTO = returnCustomerDTOBuilder(customerDTO);

        when(customerService.patchCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnCustomerDTO);

        mockMvc.perform(patch(CUSTOMER_URL_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL_BY_ID)));
    }

    @Test
    public void testDeleteCustomerById() throws Exception {

        mockMvc.perform(delete(CUSTOMER_URL_BY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void testGetByCustomerIdNotFound() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + -1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByLastNameNotFound() throws Exception {
        when(customerService.getCustomerByLastName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/lastname/Error")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByFirstNameNotFound() throws Exception {
        when(customerService.getCustomerByFirstName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(customerController.BASE_URL + "/firstname/Error")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private static CustomerDTO customerDtoBuilder(String firstName, String lastName) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);
        customerDTO.setCustomerUrl(CUSTOMER_URL_BY_ID);
        return customerDTO;
    }

    private static CustomerDTO returnCustomerDTOBuilder(CustomerDTO customerDTO) {
        CustomerDTO returnDTO = customerDtoBuilder(customerDTO.getFirstname(), customerDTO.getLastname());
        returnDTO.setCustomerUrl(CUSTOMER_URL_BY_ID);
        return returnDTO;
    }

}