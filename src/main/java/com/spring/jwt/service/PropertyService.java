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


        propertyRepository.save(property);
        return modelMapper.map(property, PropertyDTO.class);
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

            PropertyDTO propertyDTO = modelMapper.map(property, PropertyDTO.class);


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



}
