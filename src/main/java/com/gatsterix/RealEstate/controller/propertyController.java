package com.gatsterix.RealEstate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatsterix.RealEstate.dto.PropertyDTO;
import com.gatsterix.RealEstate.interfaces.IProperty;
import com.gatsterix.RealEstate.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/property")
public class propertyController {

    @Autowired
    private IProperty iproperty;


//    @PostMapping("/Add")
//    public ResponseEntity<Response> AddProperty(@RequestBody PropertyDTO propertyDTO, @RequestPart List<MultipartFile> multipartFile){
//        try{
//
//            PropertyDTO propertyDto=iproperty.saveProperty(propertyDTO,multipartFile);
//            Response response=new Response("Property Added Successfully",propertyDto,false);
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//        catch (Exception e){
//            Response erorrResponse=new Response("Failed to add a property",e.getMessage(),true);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorrResponse);
//        }
//    }

    @PostMapping("/Add")
    public ResponseEntity<Response> addProperty(
            @RequestPart String propertyDTOString,
            @RequestPart List<MultipartFile> multipartFile
    ) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            PropertyDTO propertyDTO = objectMapper.readValue(propertyDTOString, PropertyDTO.class);

            PropertyDTO savedProperty = iproperty.saveProperty(propertyDTO, multipartFile);
            Response response = new Response("Property Added Successfully", savedProperty, false);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response errorResponse = new Response("Failed to add a property", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
