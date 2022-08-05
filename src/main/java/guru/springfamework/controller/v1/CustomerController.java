package guru.springfamework.controller.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("byFirstName/{firstName}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstName){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByFirstName(firstName), HttpStatus.OK);
    }

   @GetMapping("byLastName/{lastName}")
   public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String lastName){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
   }
}
