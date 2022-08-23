package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    public static final String NAME = "tasty";
    public static final Long ID = 1L;

    @Mock
    VendorMapper vendorMapper;

    @Mock
    VendorRepository vendorRepository;

    @InjectMocks
    VendorServiceImpl vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        VendorDTO mockVendorDTO = mock(VendorDTO.class);

        when(vendorRepository.findAll()).thenReturn(vendors);
        when(vendorMapper.vendorToVendorDTO(any(Vendor.class))).thenReturn(mockVendorDTO);

        //when
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorDTOList.size());
    }

    @Test
    public void findByName() {

        //given
        VendorDTO tasty = new VendorDTO();
        tasty.setName(NAME);

        VendorDTO mockVendorDTO = mock(VendorDTO.class);

        when(vendorRepository.findByName(NAME)).thenReturn(tasty);
        when(vendorMapper.vendorToVendorDTO(any(Vendor.class))).thenReturn(mockVendorDTO);

        //when
        VendorDTO vendorDTO = vendorService.findByName(NAME);


        //then
        assertEquals(NAME, vendorDTO.getName());

    }

    @Test
    public void getVendorById(){

        //given
        Vendor tasty = new Vendor();
        tasty.setId(1L);

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(tasty));

        //when
        Vendor vendor = vendorService.getVendorById(ID);

        //then
        assertEquals(Optional.of(ID), Optional.of(vendor.getId()));

    }


}