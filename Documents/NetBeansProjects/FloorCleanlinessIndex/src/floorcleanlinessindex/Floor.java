/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floorcleanlinessindex;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.features2d.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

public class Floor {

 public static int compare(String cleanFloor, String newFloor) {
    // Set image path

    String filename1 = cleanFloor;
    String filename2 = newFloor;
  int ret;
  System.out.println();
  ret = compareFeature(filename1, filename2);
  
  if (ret > 0) {
   System.out.println("Two images are same.");
  } else {
   System.out.println("Two images are different.");
  }
  if(filename1.equals(filename2)){
      ret=0;
  }
  return ret;
 }

 /**
  * Compare that two images is similar using feature mapping  
  * @author minikim
  * @param filename1 - the first image
  * @param filename2 - the second image
  * 
  */
 public static int compareFeature(String filename1, String filename2) {
  int retVal = 0;
  long startTime = System.currentTimeMillis();
  
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  
  // Load images to compare

    Mat img1 = Highgui.imread(filename1, Highgui.CV_LOAD_IMAGE_COLOR);
    Mat img2 = Highgui.imread(filename2, Highgui.CV_LOAD_IMAGE_COLOR);
  
  // Declare key point of images
  MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
  MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
  Mat descriptors1 = new Mat();
  Mat descriptors2 = new Mat();

  // Definition of ORB key point detector and descriptor extractors
  FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB); 
  DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

  // Detect key points
  detector.detect(img1, keypoints1);
  detector.detect(img2, keypoints2);
  
  // Extract descriptors
  extractor.compute(img1, keypoints1, descriptors1);
  extractor.compute(img2, keypoints2, descriptors2);

  // Definition of descriptor matcher
  DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

  // Match points of two images
  MatOfDMatch matches = new MatOfDMatch();

  double max_dist=0;
  if (descriptors2.cols() == descriptors1.cols()) {
   matcher.match(descriptors1, descriptors2 ,matches);
 
   // Check matches of key points
   DMatch[] match = matches.toArray();
   max_dist = 0; double min_dist = 100;
   
   for (int i = 0; i < descriptors1.rows(); i++) { 
    double dist = match[i].distance;
       if( dist < min_dist ) min_dist = dist;
       if( dist > max_dist ) max_dist = dist;
   }
   System.out.println("max_dist=" + max_dist + ", min_dist=" + min_dist);
   
      // Extract good images (distances are under 10)
   for (int i = 0; i < descriptors1.rows(); i++) {
    if (match[i].distance <= 10) {
     retVal++;
    }
   }
   System.out.println("matching count=" + retVal);
  }
  else{
      
      max_dist=-1;
  }
  
  long estimatedTime = System.currentTimeMillis() - startTime;
  System.out.println("estimatedTime=" + estimatedTime + "ms");
  

  return (int)max_dist;
 }
}
