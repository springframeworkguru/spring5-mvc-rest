package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService
{
	private final VendorRepository vendorRepository;
	private final VendorMapper vendorMapper;
	

	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) 
	{
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
		
	}

	@Override
	public List<VendorDTO> getAllVendors() 
	{
		return vendorRepository.findAll()
								.stream()
								.map(vendorMapper::vendorToVendorDTO)
								.collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) 
	{
		return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) 
	{
		return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendorMapper.vendorDtoToVendor(vendorDTO)));
	}
	
	private VendorDTO updateEntity(Long id, VendorDTO vendorDTO, boolean isUpdate)
	{
		Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		
		if(isUpdate || vendorDTO.getName() != null)
		{
			vendor.setName(vendorDTO.getName());
			vendorDTO =  vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
		}

		return vendorDTO;
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		return updateEntity(id, vendorDTO, true);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		
		return updateEntity(id, vendorDTO, false);
	}

	@Override
	public void deleteVendorById(Long id) 
	{
		vendorRepository.deleteById(id);
	}
}
