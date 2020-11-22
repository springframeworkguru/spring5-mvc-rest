package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Darcy Xian  21/11/20  6:40 pm      spring5-mvc-rest
 */
@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategories ();

    CategoryDTO getCategoryByName(String name);
}
