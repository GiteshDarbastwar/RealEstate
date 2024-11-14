package com.gatsterix.RealEstate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ImageProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageID;
    private byte propertyImages;

    @ManyToOne
    @JoinColumn(name="propertyID")
    private Property property;



}
