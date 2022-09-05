package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VendorDTO> findByName(String name) {
        return Optional.ofNullable(vendorRepository.findByName(name).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

        return returnDto;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnedDTO.setVendorUrl(VendorController.BASE_URL + "/" + savedVendor.getId());

        return returnedDTO;
    }

}
