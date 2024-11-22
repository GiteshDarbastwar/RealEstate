package com.spring.jwt.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class PropertyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID propertyInfoID;
    private String propertyDescription;
    private Integer propertyAge;
    private Integer propertyBedRooms;
    private Integer propertyBathRooms;
    private String propertyOtherFeatures;



}
