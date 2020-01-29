package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

    //no id property because dont want to expose to outside world. Id will be auto-generated when created (@Id and @Generated annotation)

    private String firstName;
    private String lastName;

    //only need the customer url on DTO object cuz want to send that back to the user in JSON object
    private String customer_url;
}
