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



    @ElementCollection(fetch = FetchType.EAGER)
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List< byte[]> propertyImages;






}
