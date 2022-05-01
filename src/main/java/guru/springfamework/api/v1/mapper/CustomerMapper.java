package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Created by jt on 9/27/17.
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "customerUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);
    
    @AfterMapping
    default void addBaseURL(@MappingTarget CustomerDTO customerDTO)
	{
		customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + customerDTO.getCustomerUrl());
	} 

    @Mapping(target = "id", ignore = true)
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
