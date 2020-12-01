package guru.springfamework.controllers.v1;


import guru.springfamework.model.CustomerDTO;
import guru.springfamework.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Darcy Xian  22/11/20  5:05 pm      spring5-mvc-rest
 */
@Api(description = "This is my Customer controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
@AllArgsConstructor
public class CustomerController {

    public static final  String BASE_URL = "/api/v1/customers";


    CustomerService customerService;

    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers (){

        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomerDTOs().addAll( customerService.findAllCustomers());
        return customerListDTO;
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
    public void deleteCustomer(
            @PathVariable Long id){

        customerService.deleteCustomerById(id);

        return ;
    }
}




















































