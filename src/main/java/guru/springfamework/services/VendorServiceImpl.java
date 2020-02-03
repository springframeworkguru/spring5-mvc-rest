package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.customExceptions.ResourceAlreadyExistsException;
import guru.springfamework.customExceptions.ResourceNotFoundException;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    //get
    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> list = vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendor_url("/api/v1/vendors/" + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        VendorListDTO vendorListDTO = new VendorListDTO();
        vendorListDTO.setVendors(new ArrayList<>());
        vendorListDTO.getVendors().addAll(list);

        return vendorListDTO;
    }

    //get
    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendor_url("/api/v1/vendors/" + vendor.getId());
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    //post
    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        if(vendorRepository.findByName(vendorDTO.getName()).isPresent()){
            Vendor existingVendor = vendorRepository.findByName(vendorDTO.getName()).get();
            throw new ResourceAlreadyExistsException("A resource with the name of " + existingVendor.getName() + "already exists. Try searching for this resources' id: " + existingVendor.getId());
        }
        else{
            Vendor savedVendor = vendorRepository.save(vendorMapper.vendorDTOtoVendor(vendorDTO));
            return saveAndReturnVendorDTO(savedVendor);
        }

    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    //put
    @Override
    public VendorDTO saveVendorById(Long id, VendorDTO vendorDTO) {
        //convert to vendor, set the id and then save (Springs' save method will check if Id already exists and if so, just update it)
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnVendorDTO(vendor);
    }

    //patch
    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        VendorDTO convertedVendorDTO;
        if(vendorRepository.findById(id).isPresent()){
            Vendor vendor = vendorRepository.findById(id).get();
            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }
            convertedVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
        }
        else{
            throw new ResourceNotFoundException();
        }
        convertedVendorDTO.setVendor_url("/api/v1/vendors/" + id);
        return convertedVendorDTO;
    }

    //helper method to reduce duplication
    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        savedVendorDTO.setVendor_url("/api/v1/vendors/" + savedVendor.getId());
        return savedVendorDTO;
    }


}
