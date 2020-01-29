package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {
    CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    public void customerToCustomerDTO() {
        //arrange
        Customer customer = new Customer();
        customer.setFirstName("Zach");
        customer.setId(1L);

        //act
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //assert
        assertEquals("Zach", customerDTO.getFirstName());

    }
}