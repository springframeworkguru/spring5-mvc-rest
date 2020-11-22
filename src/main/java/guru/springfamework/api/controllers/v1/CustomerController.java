package guru.springfamework.api.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;

import guru.springfamework.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Darcy Xian  22/11/20  5:05 pm      spring5-mvc-rest
 */

@Controller
@EnableWebMvc
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    CustomerService customerService;


    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers (){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.findAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
         CustomerDTO customerDTO =  customerService.findCustomerById(id);
         customerDTO.setCustomerUrl("/api/v1/cusotmers/"+id);


        return new ResponseEntity<CustomerDTO>(
             customerDTO ,HttpStatus.OK);
    }

    @PostMapping
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id,customerDTO),HttpStatus.CREATED);

    }
}











