package com.empty.opencvexample.controller;

import com.empty.opencvexample.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class BlurController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/blurred", method = RequestMethod.GET)
    public ResponseEntity<String> detectIfImageIsBlurred() {

        Boolean imageStatus = imageService.isBlurred();

        return new ResponseEntity<>(imageStatus==Boolean.TRUE?"Blurred":"Not Blurred", HttpStatus.OK);
    }

}
