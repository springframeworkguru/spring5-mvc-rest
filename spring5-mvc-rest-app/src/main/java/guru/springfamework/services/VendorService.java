package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Darcy Xian  24/11/20  3:59 pm      spring5-mvc-rest
 */
@Service
public interface VendorService {

    List<VendorDTO> findAllVendors();

     VendorDTO createNewVendor(VendorDTO vendorDTO);

     void deleteById(Long id);

     VendorDTO findBYId(Long  id);

     VendorDTO saveById(Long id, VendorDTO vendorDTO);

     VendorDTO patchById(Long id, VendorDTO vendorDTO);
}
