package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    public static final String NAME = "Tasty";
    public static final Long ID = 1L;

    @Mock
    VendorMapper vendorMapper;

    @Mock
    VendorRepository vendorRepository;

    /*@InjectMocks
    VendorServiceImpl vendorService;*/

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorDTOList.size());
    }

    @Test
    public void findByName() {

        //given
        Vendor tasty = new Vendor();
        tasty.setName("tasty");

        when(vendorRepository.findByName("tasty")).thenReturn(tasty);

        //when
        VendorDTO vendorDTO = vendorService.findByName("tasty");

        //then
        assertEquals("tasty", vendorDTO.getName());

    }

    @Test
    public void getVendorById(){

        //given
        Vendor tasty = new Vendor();
        tasty.setId(1L);

        when(vendorRepository.findById(1L)).thenReturn(Optional.of(tasty));

        //when
        Vendor vendor = vendorService.getVendorById(1L);

        //then
        assertEquals(Optional.of(1L), Optional.of(vendor.getId()));

    }


}