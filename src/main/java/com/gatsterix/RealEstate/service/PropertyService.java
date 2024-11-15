package com.gatsterix.RealEstate.service;

import com.gatsterix.RealEstate.dto.PropertyDTO;
import com.gatsterix.RealEstate.entity.*;
import com.gatsterix.RealEstate.interfaces.IProperty;
import com.gatsterix.RealEstate.repository.PropertyInformationRepository;
import com.gatsterix.RealEstate.repository.PropertyLocationRepository;
import com.gatsterix.RealEstate.repository.PropertyOwnerDetailsRepository;
import com.gatsterix.RealEstate.repository.PropertyRepository;
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


//    @Override
//    public PropertyDTO saveProperty(PropertyDTO propertyDTO, List<MultipartFile> multipartFiles) throws IOException {
//        List<byte[]> images = new ArrayList<>();
//        for (int i = 0; i < multipartFiles.size(); i++) {
//            byte[] image = multipartFiles.get(i).getBytes();
//            images.add(image);
//        }
//        propertyDTO.setImages(images);
//
//        Property property = modelMapper.map(propertyDTO, Property.class);
//        property.setPropertyInformation(propertyDTO.getPropertyInformation());
//        PropertyInformation propertyInformation = property.getPropertyInformation();
//        PropertyLocation propertyLocation = property.getPropertyLocation();
//        PropertyOwnerDetails propertyOwnerDetails = property.getPropertyOwnerDetails();

//        propertyRepository.save(property);
//        propertyInformationRepository.save(propertyInformation);
//        propertyLocationRepository.save(propertyLocation);
//        propertyOwnerDetailsRepository.save(propertyOwnerDetails);
//        return modelMapper.map(property,PropertyDTO.class);
//
//
//
//
//
//    }


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


}
