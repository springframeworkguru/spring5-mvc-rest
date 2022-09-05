package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerMapper customerMapper, final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstName(String firstname){
        return customerMapper.customerToCustomerDTO(customerRepository.findByfirstname(firstname).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastname){
            return customerMapper.customerToCustomerDTO(customerRepository.findBylastname(lastname).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerURL(id));
                    return customerDTO;})
                    .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO){
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstname()!=null){
                customer.setFirstname(customerDTO.getFirstname());
            }
            if (customerDTO.getLastname()!=null){
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnDTO.setCustomerUrl(getCustomerURL(id));

            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnedDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnedDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnedDto;
    }

    private String getCustomerURL(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }
}
