package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Darcy Xian  22/11/20  4:46 pm      spring5-mvc-rest
 */
@Data
public class CustomerDTO {

    private Long id;
    @ApiModelProperty(value = "This is the first name",required = true)
    private String firstname;
    @ApiModelProperty(value = "This is the first name",required = false)
    private String lastname;
    private String customerUrl;
}
