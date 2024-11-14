package com.gatsterix.RealEstate.repository;

import com.gatsterix.RealEstate.entity.PropertyOwnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyOwnerDetailsRepository extends JpaRepository<PropertyOwnerDetails, UUID> {
}
