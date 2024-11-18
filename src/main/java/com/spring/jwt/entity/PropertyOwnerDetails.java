package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class PropertyOwnerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ownerDetailID;
    private String fullName;
    private String emailAddress;
    private Long phoneNUmber;

    @OneToOne
    @JoinColumn(name = "propertyID")
    @JsonBackReference
    private Property property;
}
