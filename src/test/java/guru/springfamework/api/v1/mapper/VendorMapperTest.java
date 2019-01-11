package guru.springfamework.api.v1.mapper;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;


public class VendorMapperTest 
{
	public static final Long ID = 1L;
    public static final String NAME = "Test Vendor";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception {
        //given
    	Vendor vendor = new Vendor();
    	vendor.setId(ID);
    	vendor.setName(NAME);
    	
        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID, vendorDTO.getVendorUrl());
      

    }
}
