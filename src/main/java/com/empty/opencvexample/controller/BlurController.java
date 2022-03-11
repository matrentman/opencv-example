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

        Double blurValue;
        String imagePath = "C:/Users/mikea/aidev/opencv-example/src/main/resources/child-blur.png";
        blurValue = imageService.isBlurred(imagePath);

        return new ResponseEntity<>("Blur Value = " + blurValue, HttpStatus.OK);
    }

}
