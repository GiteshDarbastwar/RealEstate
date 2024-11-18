package com.spring.jwt.repository;

import com.spring.jwt.entity.PropertyOwnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyOwnerDetailsRepository extends JpaRepository<PropertyOwnerDetails, UUID> {
}
