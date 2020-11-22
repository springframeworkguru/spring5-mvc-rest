package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Darcy Xian  21/11/20  7:42 pm      spring5-mvc-rest
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {
   List<CategoryDTO> categoryListDTOS;
}
