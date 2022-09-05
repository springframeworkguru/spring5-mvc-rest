package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/firstname/{firstname}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByFirstName(@PathVariable String firstname){
        return customerService.getCustomerByFirstName(firstname);
    }


   @GetMapping("/lastname/{lastname}")
   @ResponseStatus(HttpStatus.OK)
   public CustomerDTO getCustomerByLastName(@PathVariable String lastname){
        return customerService.getCustomerByLastName(lastname);
   }
   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
   }

   @PostMapping()
   @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createNewCustomer(customerDTO);
   }

    @PutMapping("/{id}")
    @ResponseStatus( HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus( HttpStatus.OK)
    public CustomerDTO patchUpdateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus( HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
