package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

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
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(),new Customer());

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
        customer.setFirstName(FIRST_NAME);

        when(customerRepository.findByFirstName(FIRST_NAME)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstName(FIRST_NAME);

        //then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
    }

    @Test
    public void getCustomerByLastName() {
        //given
        Customer customer= new Customer();
        customer.setLastName(LAST_NAME);

        when(customerRepository.findByLastName(LAST_NAME)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);

        //then
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}