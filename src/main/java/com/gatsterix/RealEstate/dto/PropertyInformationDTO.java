package com.gatsterix.RealEstate.dto;

import com.gatsterix.RealEstate.entity.Property;
import lombok.Data;
import java.util.UUID;

@Data
public class PropertyInformationDTO {

    private UUID propertyInfoID;
    private String propertyDescription;
    private Integer propertyAge;
    private Integer propertyBedRooms;
    private Integer propertyBathRooms;
    private String propertyOtherFeatures;

   // private Property property;
}
