package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    VendorDTO vendorDTO = new VendorDTO();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void vendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setName("tasty");

        vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals("tasty", vendorDTO.getName());
    }
}