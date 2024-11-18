package com.spring.jwt.dto;

import com.spring.jwt.entity.PropertyInformation;
import com.spring.jwt.entity.PropertyLocation;
import com.spring.jwt.entity.PropertyOwnerDetails;
import com.spring.jwt.entity.PropertyStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
public class PropertyDTO {

    private UUID propertyID;
    private String propertyTitle;
    private String propertyType;
    private Double propertyPrice;
    private Float propertyArea;
    private Integer propertyRooms;

    private PropertyStatus propertyStatus;

    private List<byte []> images;
    private PropertyInformation propertyInformation;
    private PropertyLocation propertyLocation;
    private PropertyOwnerDetails propertyOwnerDetails;


}
