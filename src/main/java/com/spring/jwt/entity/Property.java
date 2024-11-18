package com.spring.jwt.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String  propertyType;
    private Double propertyPrice;
    private Float propertyArea;
    private Integer propertyRooms;

    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List< byte[]> propertyImages;

    @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private  PropertyInformation propertyInformation;

    @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private PropertyLocation propertyLocation;

   @OneToOne(mappedBy ="property", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JsonManagedReference
   private PropertyOwnerDetails propertyOwnerDetails;





}
