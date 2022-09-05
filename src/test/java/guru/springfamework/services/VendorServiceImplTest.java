package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    public static final String NAME = "tasty";
    public static final Long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

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
        VendorDTO tasty = vendorDTOBuilder();


        when(vendorRepository.findByName(NAME)).thenReturn(Optional.of(tasty));

        //when
        Optional<VendorDTO> vendorDTO = vendorService.findByName(NAME);


        //then
        assertEquals(Optional.of(NAME), vendorDTO.map(VendorDTO::getName));

    }

    @Test
    public void getVendorById(){

        //given
        VendorDTO vendorDTO = vendorDTOBuilder();
        Vendor tasty = vendorBuilder(vendorDTO);

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(tasty));

        //when
        Vendor vendor = vendorService.getVendorById(ID);

        //then
        assertEquals(Optional.of(ID), Optional.of(vendor.getId()));

    }

    @Test
    public void createNewVendor() {
        //given
        VendorDTO vendorDTO = vendorDTOBuilder();

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(),savedDto.getName());
    }


    @Test
    public void saveVendorByDTO(){
        //given
        VendorDTO vendorDTO = vendorDTOBuilder();
        Vendor vendor = vendorBuilder(vendorDTO);


        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO savedVendorByDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedVendorByDTO.getName());
        assertEquals(VendorController.BASE_URL +"/1", savedVendorByDTO.getVendorUrl());
    }


    private static VendorDTO vendorDTOBuilder() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        return vendorDTO;
    }

    private static Vendor vendorBuilder(VendorDTO vendorDTO) {
        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setId(1L);
        return vendor;
    }
}
