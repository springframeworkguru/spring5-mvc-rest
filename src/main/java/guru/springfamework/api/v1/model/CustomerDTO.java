package guru.springfamework.api.v1.model;

import lombok.Data;

/**
 * Darcy Xian  22/11/20  4:46 pm      spring5-mvc-rest
 */
@Data
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String customerUrl;
}
