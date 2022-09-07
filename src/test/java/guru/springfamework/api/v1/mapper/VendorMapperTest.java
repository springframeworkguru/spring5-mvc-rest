package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "tasty";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    VendorDTO vendorDTO = new VendorDTO();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void vendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}