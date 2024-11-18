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


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // Convert Property entities to PropertyDTOs
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for (Property property : properties) {
            PropertyDTO propertyDTO = modelMapper.map(property, PropertyDTO.class);
            propertyDTOs.add(propertyDTO);
        }

        return propertyDTOs;
    }

    @Override
    public List<PropertyDTO> getAll() {
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for (Property property : propertyList) {
            PropertyDTO propertyDTO = modelMapper.map(property,PropertyDTO.class);
            propertyDTOList.add(propertyDTO);
        }
        return propertyDTOList;
    }




}
