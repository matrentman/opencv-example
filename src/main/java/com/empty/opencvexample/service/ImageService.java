package com.empty.opencvexample.service;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    public Boolean isBlurred() {
        Boolean isBlurred = null;

        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        //Instantiating the Imagecodecs class
        Imgcodecs imageCodecs = new Imgcodecs();

        //Reading the Image from the file
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

        return value > 1.0?Boolean.FALSE:Boolean.TRUE;
    }
}
