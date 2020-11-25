package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
/**
 * Darcy Xian  24/11/20  4:15 pm      spring5-mvc-rest
 */
public class VendorServiceTest {

    VendorService vendorService;
    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImp(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void findAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(),new Vendor(),new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);
        // when
        List<VendorDTO> vendorDTOS = vendorService.findAllVendors();
        // then
        assertEquals(3,vendorDTOS.size());
    }
    @Test
    public void createNewVendors(){
        //given
        Vendor vendor = new Vendor();
        vendor.setName("Bobb");

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
        // when
        VendorDTO vendorDTO = vendorService.createNewVendor(new VendorDTO());

        // then
        assertEquals("Bobb",vendorDTO.getName());


    }
    @Test
    public void deleteById(){
        Long id = 1L;

        vendorRepository.deleteById(id);

        verify(vendorRepository,times(1)).deleteById(anyLong());
       then(vendorRepository).should().deleteById(anyLong());
    }

    @Test
    public void findByid() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Bobb");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));
        // when
       VendorDTO vendorDTO = vendorService.findBYId(1L);
       // then
        assertEquals("Bobb",vendorDTO.getName());
    }
    @Test
    public void saveById() throws Exception{
        //given   输入的DTO
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("bobb");
        vendorDTO.setNickName("aoaa");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setNickName(vendorDTO.getNickName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
        //when
        VendorDTO vendorDTO1 = vendorService.saveById(1L,vendorDTO);
        //then

        assertEquals("bobb",vendorDTO1.getName());
        assertEquals("aoaa",vendorDTO1.getNickName());
    }
    @Test
    public void patchById(){
        // given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setNickName("aaa");
        vendor.setName("aaa");

        VendorDTO vendorDTO = new VendorDTO();

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // when
        VendorDTO vendorDTO1 = vendorService.patchById(anyLong(),vendorDTO);
        // then
        // 'should' defaults to times = 1
        verify(vendorRepository,times(1)).findById(anyLong());
        verify(vendorRepository,times(1)).save(any(Vendor.class));

    }

}




























