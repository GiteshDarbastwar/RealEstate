package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.PropertyDTO;
import com.spring.jwt.entity.PropertyStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IProperty {

    PropertyDTO saveProperty(PropertyDTO propertyDTO, List<MultipartFile> multipartFiles) throws IOException;


    List<PropertyDTO> getAll();


}
