/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author eduardo
 */
public class GreyImage {

    private BufferedImage grey;

    public GreyImage(String imgOriginal) throws IOException {
        grey = ImageIO.read(new File(imgOriginal));
    }

    /**
     * Gera os tons de cinza de uma imagem qualquer
     * @param nome nome da imagem
     */
    public void GerarTonsDeCinza() {

        for (int x = 0; x < grey.getWidth(); x++) {
            for (int y = 0; y < grey.getHeight(); y++) {

                Color cor = new Color(grey.getRGB(x, y));
                int pixel = (int) ((cor.getRed() * 0.2989)
                        + (cor.getGreen() * 0.5870)
                        + (cor.getBlue() * 0.1140));//multiplica os pesos
                pixel = (255 << 24) + (pixel << 16) + (pixel << 8) + pixel;//faz o deslocamento de bits
                grey.setRGB(x, y, pixel);//coloca o novo valor na foto
            }
        }

        // ImageIO.write(grey, "jpg", new File());//salva a foto
    }//fim do GerarTonsDCinza

    public BufferedImage getGrey() {
        return grey;
    }
}
