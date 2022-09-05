package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    //TODO what if there are a number of people with the same name - needs to be a list returned
    CustomerDTO getCustomerByFirstName(String firstname);
    CustomerDTO getCustomerByLastName(String lastname);
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}
