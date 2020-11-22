package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Darcy Xian  22/11/20  6:33 pm      spring5-mvc-rest
 */
public class CustomerMapperTest {

    private static final String FIRSTNAME = "Joe";
    private static final String LASTNAME = "Lin";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        // given
        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        customer.setId(1L);

        // when
        CustomerDTO customerDTO1 = customerMapper.CustomerToCustomerDTO(customer);

        // then
        assertEquals(Long.valueOf(1L), customerDTO1.getId());
        assertEquals(FIRSTNAME,customerDTO1.getFirstname());
        assertEquals(LASTNAME,customerDTO1.getLastname());



    }
}