package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstname){
        return new ResponseEntity<>(customerService.getCustomerByFirstName(firstname), HttpStatus.OK);
    }


   @GetMapping("/lastname/{lastname}")
   public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String lastname){
        return new ResponseEntity<>(customerService.getCustomerByLastName(lastname), HttpStatus.OK);
   }
   @GetMapping("/{id}")
   public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
   }

   @PostMapping()
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
   }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchUpdateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
