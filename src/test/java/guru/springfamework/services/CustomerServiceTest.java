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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Darcy Xian  22/11/20  6:41 pm      spring5-mvc-rest
 */
public class CustomerServiceTest {

    private static final Long ID = 2L;

    CustomerService customerService;

     @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImp(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void findAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(new Customer(),new Customer(),new Customer());

        when(customerRepository.findAll()).thenReturn(customers);
        //when
        List<CustomerDTO> customerDTOS = customerService.findAllCustomers();

        // then
        assertEquals(3,customerDTOS.size());
    }

    @Test
    public void findCustomerById() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);

        when(customerRepository.getOne(1L)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.findCustomerById(1L);

        // then
        assertEquals(Long.valueOf(1L),Long.valueOf(customerDTO.getId()));
    }

    @Test
    public void createNewCustomer() throws Exception{
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("lim");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstname(customerDTO.getFirstname());

        when(customerRepository.save(any((Customer.class)))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception{
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("lim");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstname(customerDTO.getFirstname());

        when(customerRepository.save(any((Customer.class)))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L,customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void testDeleteCustomerById() throws Exception{
        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }
}














