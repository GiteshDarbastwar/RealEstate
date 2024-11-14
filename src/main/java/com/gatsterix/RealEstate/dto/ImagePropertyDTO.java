package com.gatsterix.RealEstate.dto;

import com.gatsterix.RealEstate.entity.Property;
import lombok.Data;
import java.util.UUID;

@Data
public class ImagePropertyDTO {

    private UUID imageID;
    private byte propertyImages;
    private Property property;

}
