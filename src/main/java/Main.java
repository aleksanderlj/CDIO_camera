import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.videoio.VideoCapture;

import java.awt.*;

public class Main {
    static {
        nu.pattern.OpenCV.loadShared();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        VideoCap vc = new VideoCap();
        vc.getFrame();


    }
}
