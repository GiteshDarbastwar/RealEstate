package com.gatsterix.RealEstate.dto;

import com.gatsterix.RealEstate.entity.Property;
import lombok.Data;
import java.util.UUID;

@Data
public class PropertyOwnerDetailsDTO {

    private UUID ownerDetailID;
    private String fullName;
    private String emailAddress;
    private Long phoneNUmber;
    private Property property;
}
