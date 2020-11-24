package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * Darcy Xian  24/11/20  3:36 pm      spring5-mvc-rest
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "id", target = "id")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    @Mapping(source = "id", target = "id")
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);


}
