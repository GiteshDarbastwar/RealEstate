package com.spring.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jwt.Interfaces.IProperty;
import com.spring.jwt.dto.PropertyDTO;
import com.spring.jwt.dto.Response;
import com.spring.jwt.entity.PropertyLocation;
import com.spring.jwt.entity.PropertyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private IProperty iproperty;

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





    @GetMapping("/getAll")
    public ResponseEntity<Response> getAll(){
        try{
            List<PropertyDTO> propertyDTOList = iproperty.getAll();
            Response response = new Response("List of All Properties",propertyDTOList,false);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }catch (Exception e){
            Response errorResponse = new Response("Failed to retrieve properties", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }





}
