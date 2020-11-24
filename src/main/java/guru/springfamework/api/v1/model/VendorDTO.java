package guru.springfamework.api.v1.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Darcy Xian  24/11/20  3:30 pm      spring5-mvc-rest
 */
@Data
public class VendorDTO {


        private Long id;
        private String name;
        private String vendorUrl;

}
