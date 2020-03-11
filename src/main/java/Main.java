import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.awt.*;
import java.util.Scanner;

public class Main {
    static {
        nu.pattern.OpenCV.loadShared();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws InterruptedException {

        VideoCap vc = new VideoCap();

        try {
            //Imgcodecs.imwrite("test.png", vc.getFrame());
            vc.displayVideo();
            //vc.updateWindow(vc.isolateColor(vc.getFrame(), null, null));

        } catch (Exception e){
            e.printStackTrace();
            vc.close();
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        vc.close();
        System.out.println("Exiting...");
        System.exit(0);
    }
}
