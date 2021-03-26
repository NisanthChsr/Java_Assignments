package Assignments;
import edu.duke.*;
import java.io.*;

public class Week4AssignmentBatchGrayScale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage);

        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int r = inPixel.getRed();
            int g = inPixel.getGreen();
            int b = inPixel.getBlue();
            int average = (r + g + b) / 3;

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }

        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);

            String fileName = inImage.getFileName();
            String newFileName = "gray-" + fileName;

            outImage.setFileName(newFileName);
            outImage.save();
        }
    }
}