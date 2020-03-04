import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.awt.*;

public class Main {
    static {
        nu.pattern.OpenCV.loadShared();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {

        VideoCap vc = new VideoCap();
        Imgcodecs.imwrite("test.png", vc.getFrame());
        //vc.displayImage(vc.mat2img(vc.getFrame()));

        System.out.println("Exiting...");

        vc.
        System.exit(0);
    }
}
