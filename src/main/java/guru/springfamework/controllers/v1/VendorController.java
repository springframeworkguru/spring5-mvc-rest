package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Darcy Xian  24/11/20  4:23 pm      spring5-mvc-rest
 */
@Api(description = "This is my Vendor controller")
@RestController
@RequestMapping(VendorController.BASE_URL)
@AllArgsConstructor
public class VendorController {

    VendorService vendorService;
    public static final String BASE_URL = "/api/v1/vendors";
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors (){
    return  new VendorListDTO(vendorService.findAllVendors());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        vendorService.deleteById(id);
        return ;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO findById(@PathVariable Long id){
       VendorDTO vendorDTO = vendorService.findBYId(id);
               vendorDTO.setVendorUrl(VendorController.BASE_URL + id);
      return vendorDTO;
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateById(
            @PathVariable Long id,
            @RequestBody VendorDTO vendorDTO){
        return vendorService.saveById(id,vendorDTO);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchById(@PathVariable Long id,
                               @RequestBody VendorDTO vendorDTO){
        return vendorService.patchById(id,vendorDTO);
    }

}
