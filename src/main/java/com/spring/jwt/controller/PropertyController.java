package com.spring.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jwt.Interfaces.IProperty;
import com.spring.jwt.dto.PropertyDTO;
import com.spring.jwt.dto.Response;
import com.spring.jwt.entity.PropertyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/filter")
    public ResponseEntity<Response> filter(
            @RequestParam(required = false) String propertyStatus,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer rooms,
            @RequestParam(required = false) Integer bathRooms,
            @RequestParam(required = false) Integer bedRooms,
            @RequestParam(required = false) Double priceRange
    ) {
        try {
            PropertyStatus status = null;
            if (propertyStatus != null) {
                status = PropertyStatus.valueOf(propertyStatus.toUpperCase());
            }

            // Pass the enum type to the repository method
            List<PropertyDTO> propertyDTOList = iproperty.findFilteredProperties(status, location, type, rooms, bathRooms, bedRooms, priceRange);
            Response response = new Response("Properties retrieved successfully", propertyDTOList, false);
            return ResponseEntity.status(HttpStatus.OK).body(response); // Use HttpStatus.OK for successful retrieval
//        } catch (IllegalArgumentException e) {
//            Response errorResponse = new Response("Invalid property status provided", e.getMessage(), true);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            Response errorResponse = new Response("Failed to retrieve properties", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
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
