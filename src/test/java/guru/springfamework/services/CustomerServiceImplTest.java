package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "McGivern";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customersDTOSList = customerService.getAllCustomers();

        //then
        assertEquals(3, customersDTOSList.size());
    }

    @Test
    public void getCustomerByFirstName() {
        //given
        Customer customer = new Customer();
        customer.setFirstname(FIRST_NAME);

        when(customerRepository.findByfirstname(FIRST_NAME)).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstName(FIRST_NAME);

        //then
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
    }

    @Test
    public void getCustomerByLastName() {
        //given
        Customer customer = new Customer();
        customer.setLastname(LAST_NAME);

        when(customerRepository.findBylastname(LAST_NAME)).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);

        //then
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }

    @Test
    public void createNewCustomer() {
        //given
        CustomerDTO customerDTO = customerDTOBuilder();
        Customer savedCustomer = saveCustomerBuilder(customerDTO);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDto.getLastname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void savedCustomerByDTO() throws Exception{
        //given
        CustomerDTO customerDTO = customerDTOBuilder();
        Customer savedCustomer = saveCustomerBuilder(customerDTO);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO saveCustomerByDTO = customerService.saveCustomerByDTO(1L, customerDTOBuilder());

        //then
        assertEquals(customerDTO.getFirstname(), saveCustomerByDTO.getFirstname());
        assertEquals(customerDTO.getLastname(), saveCustomerByDTO.getLastname());
        assertEquals("/api/v1/customer/1", saveCustomerByDTO.getCustomerUrl());

    }

    @Test
    public void deleteById(){
        Long id = 1L;
        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

    private static CustomerDTO customerDTOBuilder() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRST_NAME);
        customerDTO.setLastname(LAST_NAME);
        return customerDTO;
    }

    private static Customer saveCustomerBuilder(CustomerDTO customerDTO) {
        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);
        return savedCustomer;
    }

}