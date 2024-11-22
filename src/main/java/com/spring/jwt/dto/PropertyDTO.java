package com.spring.jwt.dto;

import com.spring.jwt.entity.PropertyInformation;
import com.spring.jwt.entity.PropertyLocation;
import com.spring.jwt.entity.PropertyOwnerDetails;
import com.spring.jwt.entity.PropertyStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@Data
public class PropertyDTO {

    private UUID propertyID;
    private String propertyTitle;

    private List<byte []> images;

    private List<String> base64Images;




}
