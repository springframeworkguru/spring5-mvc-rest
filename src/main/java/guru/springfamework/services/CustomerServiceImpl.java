package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    //set the customer url here so we send the URL back to the end user on the DTO object
                    customerDTO.setCustomer_url("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }


    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> customerMapper.customerToCustomerDTO(customer))
                .orElseThrow(RuntimeException::new); //todo handle this error better
    }


    @Override

    //take in a DTO object as parameter cuz people interacting with our API will follow the DTO format (cuz thats what make visible to public)
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO savedDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        savedDTO.setCustomer_url("/api/v1/customers/" + savedCustomer.getId());

        return savedDTO;
    }
}
