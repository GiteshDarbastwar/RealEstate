package com.gatsterix.RealEstate.repository;

import com.gatsterix.RealEstate.entity.PropertyInformation;
import com.gatsterix.RealEstate.entity.PropertyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyLocationRepository extends JpaRepository<PropertyLocation, UUID> {
}
