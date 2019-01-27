package org.openimaj.MyFaceReco;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

// Face Detector Class which is main class for the program
public class FaceDetector extends JFrame {
 private static final long serialVersionUID = 1L; // UID for serialization
 private static final HaarCascadeDetector detector = new HaarCascadeDetector(); // Face Detection API class
 private Webcam webcam = null; // Webcam instance
 private BufferedImage img = null; // Image 
 private List < DetectedFace > faces = null; // List of faces it detects
 
 public FaceDetector() throws IOException {
  webcam = Webcam.getDefault(); // Get webcam instance
  webcam.setViewSize(WebcamResolution.VGA.getSize()); // Setting the size as per Webcam's resolution
  webcam.open(true); // Open webcam
  img = webcam.getImage(); // store the current display from webcam
  webcam.close();// close the webcam
  ImagePanel panel = new ImagePanel(img); //Create an image panel for fetched image
  panel.setPreferredSize(WebcamResolution.VGA.getSize()); // Set the size of the image panel
  add(panel); // Add panel for display
  setTitle("Face Recognizer"); // Title
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window on exit button
  pack(); // Enclose the display under parent windows
  setLocationRelativeTo(null); // Setting the position of frame
  setVisible(true); // Make frame visible 
 }
 
 //Face detection function
 public void detectFace() {
  JFrame fr = new JFrame("Discovered Faces"); // Frame
  faces = detector.detectFaces(ImageUtilities.createFImage(img)); // Try to detect face from captured image
  if (faces == null) { // If no face is detected
   System.out.println("No faces found in the captured image");
   return;
  }
  Iterator < DetectedFace > dfi = faces.iterator();// Iterate among all the faces detected in captured image
  while (dfi.hasNext()) {
   DetectedFace face = dfi.next();
   FImage image1 = face.getFacePatch(); // for each face patch
   ImagePanel p = new ImagePanel(ImageUtilities.createBufferedImage(image1)); // Create image from face patch
   fr.add(p); // Add the patch to frame
  }
  fr.setLayout(new FlowLayout(0)); // Set layout of the frame as "FlowLayout"
  fr.setSize(500, 500); // size
  fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window on exit button
  fr.setVisible(true); // Make frame visible 
 }
 public static void main(String[] args) throws IOException {
  new FaceDetector().detectFace(); // Initialization of program.
 }
}