package image.compress;

import java.io.File;
import java.io.IOException;

/**
 * Created by earu on 2016/12/03.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        File inFile = new File("src/main/resources/img/bigImage.jpg");
        File outFile = new File("src/main/resources/img/out.jpg");
        double scale = 1.0d;

        CompressImage compressImage = new CompressImage(inFile, outFile, scale);
        compressImage.processing();
    }
}
