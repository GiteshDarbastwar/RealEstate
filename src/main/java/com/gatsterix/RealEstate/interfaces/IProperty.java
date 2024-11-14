package com.gatsterix.RealEstate.interfaces;

import com.gatsterix.RealEstate.dto.PropertyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProperty {

    PropertyDTO saveProperty(PropertyDTO propertyDTO, List<MultipartFile >multipartFiles) throws IOException;
}
