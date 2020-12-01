package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Darcy Xian  22/11/20  4:47 pm      spring5-mvc-rest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {

    List<CustomerDTO> customerListDTOS;
}
