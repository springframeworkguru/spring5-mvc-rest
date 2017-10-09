package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 10/6/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

}
