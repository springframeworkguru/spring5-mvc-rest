package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Darcy Xian  22/11/20  5:13 pm      spring5-mvc-rest
 */

@Service
public interface CustomerService {

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);


}
