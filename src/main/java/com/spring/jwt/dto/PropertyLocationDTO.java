package com.spring.jwt.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PropertyLocationDTO {

    private UUID propertyLocationID;
    private String propertyAddress;
    private String propertyCityName;
    private String propertyState;
    private Integer propertyPinCode;

   // private Property property;

}
