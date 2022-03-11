package com.empty.opencvexample.service;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    public Double isBlurred(String imagePath) {
        Double blurValue = null;

        //Loading the OpenCV core library
        //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        OpenCV.loadLocally();

        //Instantiating the Imagecodecs class
        Imgcodecs imageCodecs = new Imgcodecs();

        //Reading the Image from the file
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
        blurValue = dev.get(0,0)[0];

        return blurValue;
    }
}
