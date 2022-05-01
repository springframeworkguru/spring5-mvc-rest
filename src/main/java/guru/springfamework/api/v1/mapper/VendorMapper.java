package guru.springfamework.api.v1.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;

@Mapper
public interface VendorMapper 
{
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

	@Mapping(source = "id", target = "vendorUrl")
    VendorDTO vendorToVendorDTO(Vendor vendor);
	
	@AfterMapping
	default void addBaseURL(@MappingTarget VendorDTO vendorDTO)
	{
		vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + vendorDTO.getVendorUrl());
	}

    @Mapping(target = "id", ignore = true)
	Vendor vendorDtoToVendor(VendorDTO vendorDTO);	

}
