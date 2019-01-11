package guru.springfamework.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;

@RestController()
@RequestMapping(VendorController.BASE_URL)
public class VendorController 
{
	public static final String BASE_URL = "/api/v1/vendors";
	
	public VendorController(VendorService vendorService) 
	{
		this.vendorService = vendorService;
	}

	private final VendorService vendorService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	List<VendorDTO> getVendors()
	{
		return vendorService.getAllVendors();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	VendorDTO createVendor(@RequestBody VendorDTO vendorDTO)
	{
		VendorDTO createdVendor = vendorService.createNewVendor(vendorDTO);
		
		return createdVendor;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	void deleteVendor(@PathVariable Long id)
	{
		vendorService.deleteVendorById(id);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	VendorDTO getVendor(@PathVariable Long id)
	{
		return vendorService.getVendorById(id);
	}	


	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO)
	{
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO)
	{
		return vendorService.patchVendor(id, vendorDTO);
	}
	
}
