package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "BILL";
    public static final String LASTNAME = "JOEL";
    public static final Long ID = 1l;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerDTO customerDTO;

    @Test
    public void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }
}