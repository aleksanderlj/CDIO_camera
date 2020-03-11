import jdk.internal.dynalink.linker.LinkerServices;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.Executors;

public class VideoCap {
    VideoCapture cam;
    ImageIcon window;
    JLabel label;


    public VideoCap(){
        this.cam = new VideoCapture(0);
        prepareWindow();
    }

    public void prepareWindow(){
        JFrame container = new JFrame("Cam");
        container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.label = new JLabel();
        container.setContentPane(label);
        container.setVisible(true);
        this.window = new ImageIcon();
        label.setIcon(window);
        Mat mat;
        mat = getFrame();
        container.setSize(mat.cols(), mat.rows());
    }

    public Mat getFrame(){
        Mat mat = new Mat();
        cam.read(mat);
        //System.out.println(String.format("Channels: %d\nDepth: %s\n", mat.channels(), mat.depth()));
        return mat;
    }

    //https://www.pyimagesearch.com/2015/09/14/ball-tracking-with-opencv/
    public Mat isolateColor(Mat mat, Scalar lower, Scalar upper){
        //Imgproc.resize();
        Imgproc.GaussianBlur(mat, mat, new Size(11,11), 0);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);

        Core.inRange(mat, lower, upper, mat);
        //Imgproc.erode(mat, mat, 2);
        //Imgproc.dilate();

        return mat;
    }

    // Make sure input mat is RGB
    public BufferedImage mat2img(Mat mat){
        byte[] data = new byte[mat.width() * mat.height() * (int)mat.elemSize()];

        BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
        //TODO byte[] biData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
        mat.get(0, 0, data);

        /*
        // bgr to rgb
        // https://www.programcreek.com/java-api-examples/index.php@source_dir=flex-blazeds-master/modules/core/src/flex/messaging/cluster/?code=zylo117/SpotSpotter/SpotSpotter-master/src/pers/zylo117/spotspotter/toolbox/Mat2BufferedImage.java
        byte b;
        for (int i = 0; i < data.length; i = i + 3) {
            b = data[i];
            data[i] = data[i + 2];
            data[i + 2] = b;
        }
         */
        image.getRaster().setDataElements(0, 0, mat.width(), mat.height(), data);
        return image;
    }

    public void updateWindow(BufferedImage img){
        window.setImage(img);
        label.repaint();
    }

    public void displayVideo(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Mat mat;
            while (true){
                if ((mat = getFrame()) != null){
                    /*
                    Scalar lower = new Scalar(0,0,0);
                    Scalar upper = new Scalar(0,0,100);
                    isolateColor(mat, lower, upper);
                    Imgproc.cvtColor(mat, mat, Imgproc.COLOR_HSV2BGR);

                     */
                    updateWindow(mat2img(mat));
                }
            }
        });
    }

    public void close(){
        cam.release();
    }
}
