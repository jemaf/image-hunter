/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author eduardo
 */
public class MyImage {

    private BufferedImage image;

    public MyImage(String imgOriginal) throws IOException {
        image = ImageIO.read(new File(imgOriginal));
    }

    /**
     * Gera os tons de cinza de uma imagem qualquer
     * @param nome nome da imagem
     */
    public void GerarTonsDeCinza() {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color cor = new Color(image.getRGB(x, y));
                int pixel = (int) ((cor.getRed() * 0.2989)
                        + (cor.getGreen() * 0.5870)
                        + (cor.getBlue() * 0.1140));//multiplica os pesos
                pixel = (255 << 24) + (pixel << 16) + (pixel << 8) + pixel;//faz o deslocamento de bits
                image.setRGB(x, y, pixel);//coloca o novo valor na foto
            }
        }

        // ImageIO.write(grey, "jpg", new File());//salva a foto
    }//fim do GerarTonsDCinza

    public BufferedImage getImg() {
        return image;
    }

    // This method returns a buffered image with the contents of an image
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        //image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see Determining If an Image Has Transparent Pixels
        //  boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            //          if (hasAlpha) {
            transparency = Transparency.BITMASK;
            //       }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            //          if (hasAlpha) {
            //             type = BufferedImage.TYPE_INT_ARGB;
            //         }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

// This method returns true if the specified image has transparent pixels
    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

    /**
     * redimensiona a imagem
     * @param grey - imagem a ser redimensionada
     * @param width - largura
     * @param height - altura
     * @return imagem redimensionada
     */
    public static Image resizeImage(BufferedImage grey, int width, int height) {

        if (grey.getWidth() == grey.getHeight()) {
            return grey.getScaledInstance(width,
                    height, BufferedImage.SCALE_SMOOTH);
        } else if (grey.getWidth() > grey.getHeight()) {
            return grey.getScaledInstance(width, -1,
                    BufferedImage.SCALE_SMOOTH);
        } else {
            return grey.getScaledInstance(-1, height,
                    BufferedImage.SCALE_SMOOTH);
        }
    }

}
