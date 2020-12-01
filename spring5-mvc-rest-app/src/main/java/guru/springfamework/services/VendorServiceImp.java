package guru.springfamework.services;

import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Darcy Xian  24/11/20  4:04 pm      spring5-mvc-rest
 */
@Service
@AllArgsConstructor
public class VendorServiceImp implements VendorService {

    VendorRepository vendorRepository;
    VendorMapper vendorMapper;

    @Override
    public List<VendorDTO> findAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                   VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                   vendorDTO.setVendorUrl(getVendorUrl(vendorDTO.getId()));
                   return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
       VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDTO( vendorRepository.save(vendorMapper.vendorDTOToVendor
                (vendorDTO)));
       vendorDTO1.setVendorUrl(getVendorUrl(vendorDTO1.getId()));
      return vendorDTO1;

    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
        return ;
    }

    @Override
    public VendorDTO findBYId(Long id) {
       return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveById(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
   Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
   VendorDTO vendorDTO1  = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        return vendorDTO1;
    }

    @Override
    public VendorDTO patchById(Long id, VendorDTO vendorDTO) {
       return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    if(vendorDTO.getNickName() != null){
                        vendor.setNickName(vendorDTO.getNickName());
                    }
                    vendorRepository.save(vendor);
                    VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO1.setVendorUrl(getVendorUrl(id));
                    return vendorDTO1;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public static String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/"+ id;
    }
}
























