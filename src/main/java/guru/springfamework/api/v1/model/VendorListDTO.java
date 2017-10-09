package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jt on 10/8/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {

    List<VendorDTO> vendors;
}
