package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    //TODO what if there are a number of people with the same name - needs to be a list returned
    CustomerDTO getCustomerByFirstName(String firstName);
    CustomerDTO getCustomerByLastName(String lastName);
    CustomerDTO getCustomerById(Long id);
}
