package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping()
    public ResponseEntity<VendorListDTO> getAllVendors(){
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.getAllVendors()), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<VendorDTO>> findByName(@PathVariable String name){
        return new ResponseEntity<>(vendorService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> findById(@PathVariable Long id){
        return new ResponseEntity<>(vendorService.getVendorById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<VendorDTO> createNewVendor(@RequestBody VendorDTO vendorDTO ){
        return new ResponseEntity<>(vendorService.createNewVendor(vendorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return new ResponseEntity<VendorDTO>(vendorService.saveVendorByDTO(id, vendorDTO), HttpStatus.OK);
    }

}
