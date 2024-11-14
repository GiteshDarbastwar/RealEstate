package com.gatsterix.RealEstate.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID propertyID;
    private String propertyTitle;
    private String propertyStatus;
    private String propertyType;
    private Double propertyPrice;
    private Float propertyArea;
    private Integer propertyRooms;

    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ImageProperty> images;

    @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  PropertyInformation propertyInformation;

    @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private PropertyLocation propertyLocation;

    @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private PropertyOwnerDetails propertyOwnerDetails;





}
