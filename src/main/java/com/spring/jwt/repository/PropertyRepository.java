package com.spring.jwt.repository;

import com.spring.jwt.entity.Property;
import com.spring.jwt.entity.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {


//    @Query("SELECT p FROM Property p " +
//            "JOIN p.propertyLocation pl " +
//            "WHERE (:propertyStatus IS NULL OR p.propertyStatus = :propertyStatus) " +
//            "AND (:location IS NULL OR pl.propertyCityName = :location) " +
//            "AND (:type IS NULL OR p.propertyType = :type) " +
//            "AND (:rooms IS NULL OR p.propertyRooms = :rooms) " +
//            "AND (:bathRooms IS NULL OR p.propertyInformation.propertyBathRooms = :bathRooms) " +
//            "AND (:bedRooms IS NULL OR p.propertyInformation.propertyBedRooms = :bedRooms) " +
//            "AND (:priceRange IS NULL OR p.propertyPrice <= :priceRange)")
//    List<Property> findFilteredProperties(
//            @Param("propertyStatus") PropertyStatus status, // Change here
//            @Param("location") String location,
//            @Param("type") String type,
//            @Param("rooms") Integer rooms,
//            @Param("bathRooms") Integer bathRooms,
//            @Param("bedRooms") Integer bedRooms,
//            @Param("priceRange") Double priceRange);
//
//
//
//    @Query("SELECT p FROM Property p WHERE p.propertyOwnerDetails.fullName = ?1")
//    Optional<Property> findByFullName(String fullName);
//
//    @Query("SELECT p FROM Property p WHERE p.propertyPrice <= :propertyPrice")
//    List<Property> findBYRange(Double propertyPrice);
//}
}