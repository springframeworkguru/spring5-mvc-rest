
package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;

import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.model.CustomerDTO;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;

import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


/**
 * Darcy Xian  23/11/20  10:35 am      spring5-mvc-rest
 */
@RunWith(SpringRunner.class)
@DataJpaTest // It brings up a smaller set of the Spring Boot Context,where it essentially sets up the data layer.
public class CustomerServiceImpIT {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository,vendorRepository);
        bootstrap.run(); // loade data

        customerService = new CustomerServiceImp(customerRepository, CustomerMapper.INSTANCE);
    }
    @Test
    public void patchCustomerUpdateFirstName() throws Exception{
        String updateName = "UpdateName";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updateName);

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);

        assertNotEquals(originalFirstName,updatedCustomer.getFirstname());
        assertEquals(originalLastName,updatedCustomer.getLastname());

    }
    @Test
    public void patchCustomerUpdateLastName() throws Exception{
        String updateName = "UpdateName";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updateName);

        customerService.patchCustomer(id,customerDTO);

        // 从新找出修改后的记录
        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);

        assertEquals(originalFirstName,updatedCustomer.getFirstname());
        assertNotEquals(originalLastName,updatedCustomer.getLastname());
    }

    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers Found:" + customers.size());

        // return first id
        return customers.get(0).getId();

    }


}

