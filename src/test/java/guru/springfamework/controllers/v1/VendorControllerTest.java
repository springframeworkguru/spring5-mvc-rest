package guru.springfamework.controllers.v1;


import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Darcy Xian  24/11/20  4:34 pm      spring5-mvc-rest
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
// which bring up a small segment of the Spring Context and just for the web Front-end.
public class VendorControllerTest extends AbstractRestControllerTest {

    private static final String NAME = "kkkk";
    private static final String NICKNAME = "bbbb";
    @MockBean // provided by Spring Context  no need to init anymore
    VendorService vendorService;

    @Autowired // 和上面相呼应  vendorController.class 会被 autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getAllVendors() throws Exception {
       // given
        List<VendorDTO> vendorDTOss = Arrays.asList(new VendorDTO(),new VendorDTO(),new VendorDTO());

        when(vendorService.findAllVendors()).thenReturn(vendorDTOss);

        // when then
        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorDTOS",hasSize(3)));

    }
    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setNickName(NICKNAME);

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(vendorDTO);


        //when then
        mockMvc.perform(post(VendorController.BASE_URL)
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(asJsonString(vendorDTO)))
                         .andExpect(status().isCreated())
                         .andExpect(jsonPath("$.name",equalTo(NAME)))
                         .andExpect(jsonPath("$.nickName",equalTo(NICKNAME)));
    }
    @Test
    public void deleteById() throws Exception{
       mockMvc.perform(delete(VendorController.BASE_URL+"/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
        verify(vendorService).deleteById(anyLong());
    }
    @Test
    public void findById() throws Exception{
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setNickName(NICKNAME);

        when(vendorService.findBYId(anyLong())).thenReturn(vendorDTO);

        // when then
        mockMvc.perform(get(VendorController.BASE_URL+"/"+anyLong())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)))
                .andExpect(jsonPath("$.nickName",equalTo(NICKNAME)));
    }
   @Test
    public void upDateById() throws Exception{
       //given
       VendorDTO vendorDTO = new VendorDTO();
       vendorDTO.setId(1L);
       vendorDTO.setName(NAME);
       vendorDTO.setNickName(NICKNAME);

       when(vendorService.saveById(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO);
       // when then
       mockMvc.perform(put(VendorController.BASE_URL+"/"+ 1)
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(asJsonString(vendorDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name",equalTo(NAME)))
               .andExpect(jsonPath("$.nickName",equalTo(NICKNAME)));
   }
   @Test
    public void patchById() throws Exception{
        // given
       VendorDTO vendorDTO = new VendorDTO();
       vendorDTO.setId(1L);
       vendorDTO.setName(NAME);
       vendorDTO.setNickName(NICKNAME);

       when(vendorService.patchById(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO);

      // when then
       mockMvc.perform(patch(VendorController.BASE_URL+ "/" + 1)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(asJsonString(vendorDTO)))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.name",equalTo(vendorDTO.getName())))
                      .andExpect(jsonPath("$.nickName",equalTo(vendorDTO.getNickName())));



    }


}






























