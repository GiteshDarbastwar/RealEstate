package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

}