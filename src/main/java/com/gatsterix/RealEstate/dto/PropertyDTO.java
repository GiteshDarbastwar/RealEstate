package com.gatsterix.RealEstate.dto;

import com.gatsterix.RealEstate.entity.PropertyInformation;
import com.gatsterix.RealEstate.entity.PropertyLocation;
import com.gatsterix.RealEstate.entity.PropertyOwnerDetails;
import lombok.Data;
import java.util.List;
import java.util.UUID;


@Data
public class PropertyDTO {

    private UUID propertyID;
    private String propertyTitle;
    private String propertyStatus;
    private String propertyType;
    private Double propertyPrice;
    private Float propertyArea;
    private Integer propertyRooms;
    private List<byte []> images;
    private PropertyInformation propertyInformation;
    private PropertyLocationDTO propertyLocation;
    private PropertyOwnerDetailsDTO propertyOwnerDetails;


}
