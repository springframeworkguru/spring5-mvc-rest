package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Darcy Xian  22/11/20  5:24 pm      spring5-mvc-rest
 */
@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> findAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.CustomerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("api/vq/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return  customerRepository
                .findById(id)
                .map(customerMapper::CustomerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }
}
