package com.gatsterix.RealEstate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class PropertyLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID propertyLocationID;
    private String propertyAddress;
    private String propertyCityName;
    private String propertyState;
    private Integer propertyPinCode;

    @OneToOne
    @JoinColumn(name = "propertyID")
    private Property property;
}
