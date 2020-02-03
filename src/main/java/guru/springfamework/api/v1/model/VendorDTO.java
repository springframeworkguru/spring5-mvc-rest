package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendorDTO {

    @Builder
    public VendorDTO(String name, String vendor_url) {
        this.name = name;
        this.vendor_url = vendor_url;
    }

    private String name;

    //not necessary, but in case need to reference in future. Specifies which Json property this property maps to
    @JsonProperty("vendor_url")
    private String vendor_url;
}
