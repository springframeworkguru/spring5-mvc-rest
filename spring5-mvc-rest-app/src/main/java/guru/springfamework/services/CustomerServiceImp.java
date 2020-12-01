package guru.springfamework.services;

import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.api.v1.mapper.CustomerMapper;

import guru.springfamework.domain.Customer;
import guru.springfamework.model.CustomerDTO;
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
                    customerDTO.setCustomerUrl( getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return  customerRepository
                .findById(id)
                .map(customerMapper::CustomerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDTO = customerMapper.CustomerToCustomerDTO(savedCustomer);

        returnDTO.setCustomerUrl(getCustomerUrl( savedCustomer.getId()));

        return returnDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
         customerDTO.setId(id);

        CustomerDTO customerDTO1 = customerMapper.CustomerToCustomerDTO(
                customerRepository.save(
                        customerMapper.CustomerDTOToCustomer(customerDTO)));

        customerDTO1.setCustomerUrl(getCustomerUrl( id));
        return customerDTO1;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer ->{
            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }


                 CustomerDTO customerDTO1 = customerMapper.CustomerToCustomerDTO(customerRepository.save(customer));
                 customerDTO1.setCustomerUrl(getCustomerUrl(id));
                  return  customerDTO1;
        }).orElseThrow(ResourceNotFoundException::new);

        }
        @Override
        public void deleteCustomerById(Long id){
            customerRepository.deleteById(id);
        }

    private String getCustomerUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }


    }














































