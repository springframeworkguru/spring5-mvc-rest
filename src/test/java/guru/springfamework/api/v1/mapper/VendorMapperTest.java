package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import java.util.NavigableMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Darcy Xian  24/11/20  3:42 pm      spring5-mvc-rest
 */
public class VendorMapperTest {

    private static final String NAME = "JJJ";
    private static final String URL = "kkkk";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {
        //given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);
        vendor.setVendorUrl(URL);

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals(Long.valueOf(1L),vendorDTO.getId());
        assertEquals(NAME,vendorDTO.getName());
        assertEquals(URL,vendorDTO.getVendorUrl());
    }

    @Test
    public void vendorDTOToVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(URL);

        // when
        Vendor vendor1 = vendorMapper.vendorDTOToVendor(vendorDTO);

        // then
        assertEquals(Long.valueOf(1L),vendor1.getId());
        assertEquals(NAME,vendor1.getName());
        assertEquals(URL,vendor1.getVendorUrl());
    }
}