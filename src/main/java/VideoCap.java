import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoCap {
    VideoCapture cam;
    JFrame window;

    public VideoCap(){
        cam = new VideoCapture(0);
        window = new JFrame("Cam");
    }

    public Mat getFrame(){
        Mat mat = new Mat();
        cam.read(mat);
        System.out.println(String.format("Channels: %d\nDepth: %s\n", mat.channels(), mat.depth()));
        return mat;
    }

    public BufferedImage mat2img(Mat mat){
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
        byte[] data = new byte[mat.width() * mat.height() * (int)mat.elemSize()];
        image.getRaster().setDataElements(0, 0, mat.width(), mat.height(), data);
        return image;
    }

    public void displayImage(BufferedImage img){
        window.getContentPane().add(new JLabel(new ImageIcon(img)));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(img.getWidth(), img.getHeight());
        window.setVisible(true);
    }
}
