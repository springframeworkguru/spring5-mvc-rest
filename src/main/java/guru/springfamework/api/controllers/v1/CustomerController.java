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

@RestController
@RequestMapping(CustomerController.BASE_URL)
@AllArgsConstructor
public class CustomerController {

    public static final  String BASE_URL = "/api/v1/customers";


    CustomerService customerService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers (){
        return new CustomerListDTO(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id){
         CustomerDTO customerDTO =  customerService.findCustomerById(id);
         customerDTO.setCustomerUrl(BASE_URL + id);


        return customerDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public CustomerDTO updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id,customerDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public CustomerDTO patchCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(id,customerDTO);
    }


    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    // @RequestBody tell Sping mVC to look at the body of the request and parse it and try to create a
    // CustomerDTO out of that
    public Void deleteCustomer(
            @PathVariable Long id){

        customerService.deleteCustomerById(id);

        return null;
    }
}




















































