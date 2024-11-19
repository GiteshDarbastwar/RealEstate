package com.spring.jwt.service;


import com.spring.jwt.Interfaces.IProperty;
import com.spring.jwt.dto.PropertyDTO;
import com.spring.jwt.entity.*;
import com.spring.jwt.repository.PropertyInformationRepository;
import com.spring.jwt.repository.PropertyLocationRepository;
import com.spring.jwt.repository.PropertyOwnerDetailsRepository;
import com.spring.jwt.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PropertyService implements IProperty {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyInformationRepository propertyInformationRepository;

    @Autowired
    private PropertyOwnerDetailsRepository propertyOwnerDetailsRepository;

    @Autowired
    private PropertyLocationRepository propertyLocationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO, List<MultipartFile> multipartFiles) throws IOException {
        // Process and set images
        List<byte[]> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            images.add(multipartFile.getBytes());
        }
        propertyDTO.setImages(images);
        Property property = modelMapper.map(propertyDTO, Property.class);

        PropertyInformation propertyInformation = property.getPropertyInformation();
        if (propertyInformation != null) {
            propertyInformation.setProperty(property);
        }

        PropertyLocation propertyLocation = property.getPropertyLocation();
        if (propertyLocation != null) {
            propertyLocation.setProperty(property);
        }

        PropertyOwnerDetails propertyOwnerDetails = property.getPropertyOwnerDetails();
        if (propertyOwnerDetails != null) {
            propertyOwnerDetails.setProperty(property);
        }
        propertyRepository.save(property);
        return modelMapper.map(property, PropertyDTO.class);
    }


    @Override
    public List<PropertyDTO> findFilteredProperties(PropertyStatus propertyStatus, String location, String type, Integer rooms, Integer bathRooms, Integer bedRooms, Double priceRange) {
        // Fetch filtered properties
        List<Property> properties = propertyRepository.findFilteredProperties(propertyStatus, location, type, rooms, bathRooms, bedRooms, priceRange);

        // Log the number of properties retrieved
        System.out.println("Number of properties retrieved: " + properties.size());

        // Convert Property entities to PropertyDTOs
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for (Property property : properties) {
            PropertyDTO propertyDTO = modelMapper.map(property, PropertyDTO.class);

            System.out.println("Inside loop");

            if (property.getPropertyImages() != null) {
                List<String> base64Images = new ArrayList<>();
                for (byte[] image : property.getPropertyImages()) {
                    System.out.println("Processing image");
                    if (image != null) {
                        String base64Image = Base64.getEncoder().encodeToString(image);
                        base64Images.add(base64Image);
                    }
                }
                propertyDTO.setBase64Images(base64Images); // Set Base64 strings to DTO
            }

            // Add the populated propertyDTO to the list
            propertyDTOs.add(propertyDTO);
        }

        // Log the final size of propertyDTOs
        System.out.println("Number of PropertyDTOs created: " + propertyDTOs.size());
        return propertyDTOs;
    }

//    @Override
//    public List<PropertyDTO> getAll() {
//        List<Property> propertyList = propertyRepository.findAll();
//        List<PropertyDTO> propertyDTOList = new ArrayList<>();
//
//        for (Property property : propertyList) {
//            PropertyDTO propertyDTO = modelMapper.map(property,PropertyDTO.class);
//            propertyDTOList.add(propertyDTO);
//        }
//        return propertyDTOList;
//    }

    @Override
    public List<PropertyDTO> getAll() {
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for (Property property : propertyList) {
            // Map Property to PropertyDTO
            PropertyDTO propertyDTO = modelMapper.map(property, PropertyDTO.class);

            // Convert each byte[] in propertyImages to Base64 String
            if (property.getPropertyImages() != null) {
                List<String> base64Images = new ArrayList<>();
                for (byte[] image : property.getPropertyImages()) {
                    if (image != null) {
                        String base64Image = Base64.getEncoder().encodeToString(image);
                        base64Images.add(base64Image);
                    }
                }
                propertyDTO.setBase64Images(base64Images); // Set Base64 strings to DTO
            }

            propertyDTOList.add(propertyDTO);
        }
        return propertyDTOList;
    }

    @Override
   public PropertyDTO updateAny(UUID productID, String fullName, PropertyDTO propertyDTO){
        Property property = propertyRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productID));

        Property ownerName =  propertyRepository.findByFullName(fullName)
                .orElseThrow(() -> new RuntimeException("Owner with Name not Found :" + fullName));

        // Update fields conditionally based on non-null values in PropertyDTO
        if (propertyDTO.getPropertyTitle() != null) {
            property.setPropertyTitle(propertyDTO.getPropertyTitle());
        }
        if (propertyDTO.getPropertyStatus() != null) {
            property.setPropertyStatus(propertyDTO.getPropertyStatus());
        }
        if (propertyDTO.getPropertyType() != null) {
            property.setPropertyType(propertyDTO.getPropertyType());
        }
        if (propertyDTO.getPropertyPrice() != null) {
            property.setPropertyPrice(propertyDTO.getPropertyPrice());
        }
        if (propertyDTO.getPropertyArea() != null) {
            property.setPropertyArea(propertyDTO.getPropertyArea());
        }
        if (propertyDTO.getPropertyRooms() != null) {
            property.setPropertyRooms(propertyDTO.getPropertyRooms());
        }

        // Update property information
        if (propertyDTO.getPropertyInformation() != null) {
            PropertyInformation info = propertyDTO.getPropertyInformation();
            if (info.getPropertyDescription() != null) {
                property.getPropertyInformation().setPropertyDescription(info.getPropertyDescription());
            }
            if (info.getPropertyAge() != null) {
                property.getPropertyInformation().setPropertyAge(info.getPropertyAge());
            }
            if (info.getPropertyBedRooms() != null) {
                property.getPropertyInformation().setPropertyBedRooms(info.getPropertyBedRooms());
            }
            if (info.getPropertyBathRooms() != null) {
                property.getPropertyInformation().setPropertyBathRooms(info.getPropertyBathRooms());
            }
            if (info.getPropertyOtherFeatures() != null) {
                property.getPropertyInformation().setPropertyOtherFeatures(info.getPropertyOtherFeatures());
            }
        }

        // Update property location
        if (propertyDTO.getPropertyLocation() != null) {
            PropertyLocation location = propertyDTO.getPropertyLocation();
            if (location.getPropertyAddress() != null) {
                property.getPropertyLocation().setPropertyAddress(location.getPropertyAddress());
            }
            if (location.getPropertyCityName() != null) {
                property.getPropertyLocation().setPropertyCityName(location.getPropertyCityName());
            }
            if (location.getPropertyState() != null) {
                property.getPropertyLocation().setPropertyState(location.getPropertyState());
            }
            if (location.getPropertyPinCode() != null) {
                property.getPropertyLocation().setPropertyPinCode(location.getPropertyPinCode());
            }
        }

        // Update property owner details
        if (propertyDTO.getPropertyOwnerDetails() != null) {
            PropertyOwnerDetails ownerDetails = propertyDTO.getPropertyOwnerDetails();
            if (ownerDetails.getFullName() != null) {
                property.getPropertyOwnerDetails().setFullName(ownerDetails.getFullName());
            }
            if (ownerDetails.getEmailAddress() != null) {
                property.getPropertyOwnerDetails().setEmailAddress(ownerDetails.getEmailAddress());
            }
            if (ownerDetails.getPhoneNUmber() != null) {
                property.getPropertyOwnerDetails().setPhoneNUmber(ownerDetails.getPhoneNUmber());
            }
        }

        // Save the updated property
        Property updatedProperty = propertyRepository.save(property);

        // Map updated property to PropertyDTO
        return modelMapper.map(updatedProperty, PropertyDTO.class);

    }

}
