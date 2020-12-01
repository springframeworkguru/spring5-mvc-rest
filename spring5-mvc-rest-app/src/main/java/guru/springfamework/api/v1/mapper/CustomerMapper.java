package guru.springfamework.api.v1.mapper;


import guru.springfamework.domain.Customer;
import guru.springfamework.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Darcy Xian  22/11/20  4:51 pm      spring5-mvc-rest
 */
@Mapper
public interface CustomerMapper {

     CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

     @Mapping(source = "id",target = "id")
     CustomerDTO CustomerToCustomerDTO(Customer customer);

     @Mapping(source = "id",target = "id")
     Customer CustomerDTOToCustomer(CustomerDTO customerDTO);

}
