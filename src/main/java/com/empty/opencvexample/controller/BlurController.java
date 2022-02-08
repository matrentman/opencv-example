package com.empty.opencvexample.controller;

import com.empty.opencvexample.service.ImageService;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class BlurController {

    @RequestMapping(value = "/blurred", method = RequestMethod.GET)
    public ResponseEntity<String> detectIfImageIsBlurred() {
        String imageStatus = null;

        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        //Instantiating the Imagecodecs class
        Imgcodecs imageCodecs = new Imgcodecs();

        //Reading the Image from the file
        //String imagePath = "C:\\Users\\mikea\\aidev\\opencv-example\\src\\main\\resources\\dog-bike-car.jpg";
        String imagePath = "C:/Users/mikea/aidev/opencv-example/src/main/resources/child-blur.png";
        Mat image = imageCodecs.imread(imagePath);

        Mat destination = new Mat();
        Mat matGray=new Mat();

        Imgproc.cvtColor(image, matGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.Laplacian(matGray, destination, 3);
        MatOfDouble median = new MatOfDouble();
        MatOfDouble std= new MatOfDouble();
        Core.meanStdDev(destination, median , std);

        Math.pow(std.get(0,0)[0],2);

        // Second method
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        Mat lap = new Mat();
        Imgproc.Laplacian(gray, lap, CvType.CV_64F);

        MatOfDouble mean = new MatOfDouble ();
        MatOfDouble dev = new MatOfDouble ();

        Core.meanStdDev(lap, mean, dev);
        double value = dev.get(0,0)[0];

        return new ResponseEntity<>(imageStatus, HttpStatus.OK);
    }

}
