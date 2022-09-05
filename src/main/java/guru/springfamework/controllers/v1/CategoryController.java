package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK);
    }

}
