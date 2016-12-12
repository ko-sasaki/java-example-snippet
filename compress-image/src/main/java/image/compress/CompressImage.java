package image.compress;

import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ko-sasaki on 2016/12/03.
 */
@RequiredArgsConstructor
public class CompressImage {

    private final File inFile;

    private final File outFile;

    private final double scale;

    private String format;

    private int type;

//    public CompressImage(File inFile, File outFile , double scale){
//        this.inFile = inFile;
//        this.outFile = outFile;
//        this.scale = scale;
//    }

    public void processing() throws IOException {

        BufferedImage inImage = ImageIO.read(this.inFile);
        this.type = inImage.getType();
        this.format = getFormat();

        String[] formatNames = ImageIO.getReaderFormatNames();

        AreaAveragingScaleFilter scaleFilter = new AreaAveragingScaleFilter((int) (inImage.getWidth() * this.scale), (int) (inImage.getHeight() * this.scale));
        ImageProducer producer = new FilteredImageSource(inImage.getSource(),scaleFilter);
        Image outImage = Toolkit.getDefaultToolkit().createImage(producer);
        BufferedImage bufferedImage = new BufferedImage(outImage.getWidth(null), outImage.getHeight(null), this.type);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(outImage,0,0,null);
        graphics.dispose();
        ImageIO.write(bufferedImage,this.format,this.outFile);

    }

    private String getFormat() throws IOException {
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(new FileImageInputStream(this.inFile));
        if(imageReaders.hasNext()){
            ImageReader next = imageReaders.next();
            return next.getFormatName();
        }

        return "";
    }

}
