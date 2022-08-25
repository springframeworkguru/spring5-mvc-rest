package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

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
        VendorMapper vendorMapper1 = VendorMapper.INSTANCE;
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

    @Test
    public void createNewVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("tasty");

        Vendor savedVendor = new Vendor();
        savedVendor.setName("tasty");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(),savedVendor.getName());
    }


}