package guru.springfamework.api.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;

import guru.springfamework.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Darcy Xian  22/11/20  5:05 pm      spring5-mvc-rest
 */
@Controller
@RequestMapping("/api/v1/customers/")
@AllArgsConstructor
public class CustomerController {

    CustomerService customerService;


    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers (){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.findAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
         return new ResponseEntity<CustomerDTO>(
                 customerService.findCustomerById(id),HttpStatus.OK
         );
    }
}
