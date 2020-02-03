package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//simple tests to make sure MapStruct is converting properly
public class VendorMapperTest {

    @Test
    public void vendorToVendorDTO(){
        //arrange
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Zach");

        //act
        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);

        //assert
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOtoVendor(){
        VendorDTO vendorDTO = VendorDTO.builder().name("Zach").vendor_url("www.fake.com").build();

        Vendor vendor = VendorMapper.INSTANCE.vendorDTOtoVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
