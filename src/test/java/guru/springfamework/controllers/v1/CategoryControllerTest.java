package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.services.CategoryService;
import guru.springfamework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CategoryController.class})
public class CategoryControllerTest {

    public static final String NAME1 = "Jim";
    public static final long ID = 1L;
    public static final String NAME2 = "Bob";
    public static final long ID2 = 2L;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    CategoryDTO categoryDTO1;
    CategoryDTO categoryDTO2;

    @Before
    public void setUp() throws Exception {
        categoryDTO1 = buildCategoryDTO(ID, NAME1);
        categoryDTO2 = buildCategoryDTO(ID2, NAME2);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryDTO> categories = Arrays.asList(categoryDTO1,categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        when(categoryService.getCategoryByName(NAME1)).thenReturn(categoryDTO1);

        mockMvc.perform(get(CategoryController.BASE_URL+ "/" +NAME1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME1)));
    }

    private static CategoryDTO buildCategoryDTO(long id, String name1) {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(id);
        categoryDTO1.setName(name1);
        return categoryDTO1;
    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CategoryController.BASE_URL + "/Error")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}