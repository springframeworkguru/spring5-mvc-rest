package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.customExceptions.ResourceAlreadyExistsException;
import guru.springfamework.customExceptions.ResourceNotFoundException;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {
    @Mock
    VendorRepository vendorRepository;
    VendorService vendorService;
    List<Vendor> vendorList;
    Vendor vendor1;
    Vendor vendor2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);

        vendorList = new ArrayList<>();

        vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("vendor1");

        vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("vendor2");

        vendorList.add(vendor1);
        vendorList.add(vendor2);
    }

    @Test
    public void getAllVendors() {
        //arrange
        when(vendorRepository.findAll()).thenReturn(vendorList);

        //act
        VendorListDTO vendorDTOList = vendorService.getAllVendors();

        //assert
        assertEquals(2, vendorDTOList.getVendors().size());
        assertEquals("vendor1", vendorDTOList.getVendors().get(0).getName());
        assertEquals("vendor2", vendorDTOList.getVendors().get(1).getName());
    }

    @Test
    public void getVendorById() {
        //arrange
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));

        //act
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        //assert
        assertEquals("vendor1", vendorDTO.getName());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound(){
        //act
        vendorService.getVendorById(3L);
        //should throw error
    }

    @Test
    public void createNewVendor(){
        //arrange
        Vendor newVendor = new Vendor();
        newVendor.setId(3L);
        newVendor.setName("vendor3");
        VendorDTO newVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(newVendor);
        newVendorDTO.setVendor_url("api/v1/vendors/3L");
        when(vendorRepository.save(any())).thenReturn(newVendor);

        //act
        VendorDTO createdVendorDTO = vendorService.createNewVendor(newVendorDTO);

        //assert
        assertEquals("vendor3", createdVendorDTO.getName());
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void createNewVendorAlreadyExists(){
        //arrange
        Vendor newVendor = new Vendor();
        newVendor.setId(1L);
        newVendor.setName(vendor1.getName());
        VendorDTO newVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(newVendor);
        newVendorDTO.setName(vendor1.getName());
        newVendorDTO.setVendor_url("api/v1/vendors/1");
        when(vendorRepository.findByName(anyString())).thenReturn(Optional.ofNullable(vendor1));

        //act
        vendorService.createNewVendor(newVendorDTO);

        //assert
            //should go boom
    }

    @Test
    public void deleteVendorById(){
        //act
        vendorService.deleteVendorById(1L);
        //assert
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void saveVendorById(){
        VendorDTO vendorDTO1 = VendorMapper.INSTANCE.vendorToVendorDTO(vendor1);
        vendorDTO1.setName("fizzbuzz");
        vendor1 = VendorMapper.INSTANCE.vendorDTOtoVendor(vendorDTO1);
        when(vendorRepository.save(any())).thenReturn(vendor1);
        //act
        VendorDTO savedVendorDTO = vendorService.saveVendorById(1L, vendorDTO1);
        //assert
        assertEquals("fizzbuzz", savedVendorDTO.getName());

    }

    @Test
    public void patchVendorName() throws Exception {
        //arrange
        String updatedName = "UpdatedName";
        Long id = vendor1.getId();
        Vendor originalVendor = vendor1;
        String originalName = originalVendor.getName();
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor1));

        //act
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(updatedName);
        vendorService.patchVendor(id, vendorDTO);

        Vendor updatedVendor = vendorRepository.findById(id).get();

        //assert
        assertNotNull(updatedVendor);
        assertEquals(updatedName, updatedVendor.getName());
        assertThat(originalName, not(equalTo(updatedVendor.getName())));
    }

}