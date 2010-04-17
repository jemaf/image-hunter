/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author eduardo
 */
public class MyImage {

    private BufferedImage image;
    private int[][] frequencia;

    public MyImage(String imgOriginal) throws IOException {
        image = ImageIO.read(new File(imgOriginal));
        this.frequencia = new int[256][3];
        this.calculaFrequencia();
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

    public int[][] getFrequencia() {
        return this.frequencia;
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

        /**
     * Equaliza o histograma
     */
    public void equalizar() {
        int freqA[][] = new int[256][3];
        int freqEq[][] = new int[256][3];

        // aplica a soma acumulada
        for(int i = 0; i < frequencia.length; i++) {

            freqA[i][0] += i == 0 ? this.frequencia[i][0] :
                this.frequencia[i][0] + freqA[i-1][0];
            freqA[i][1] += i == 0 ? this.frequencia[i][1] :
                this.frequencia[i][1] + freqA[i-1][1];
            freqA[i][2] += i == 0 ? this.frequencia[i][2] :
                this.frequencia[i][2] + freqA[i-1][2];
        }

        //define numero de pixels para cada cor
        int npR = freqA[freqA.length][0], npG = freqA[freqA.length][1],
                npB = freqA[freqA.length][2];

        //divide a frequencia pelo total d pixels e multiplica pelo numero de tons
        for(int i = 0; i < frequencia.length; i++) {
            freqA[i][0] = (int)(((double)freqA[i][0] / (double)(npR)) * (double)this.frequencia.length);
            freqA[i][1] = (int)(((double)freqA[i][1] / (double)(npG)) * (double)this.frequencia.length);
            freqA[i][2] = (int)(((double)freqA[i][2] / (double)(npB)) * (double)this.frequencia.length);
        }

        //gera nova frequencia
        for(int i = 0; i < freqEq.length; i++) {

            int newValR = 0;
            int newValG = 0;
            int newValB = 0;

            for(int k = 0; k < freqA.length; k++) {
                if(freqA[k][0] == i)
                    newValR += this.frequencia[k][0];
                if(freqA[k][1] == i)
                    newValG += this.frequencia[k][1];
                if(freqA[k][2] == i)
                    newValB += this.frequencia[k][2];
            }

            freqEq[i][0] = newValR;
            freqEq[i][1] = newValG;
            freqEq[i][2] = newValB;

        }

        this.frequencia = freqEq;

        //falta implementar, aplicar novos tons na imagem
        for(int i = 0; i < this.image.getWidth(); i++)
            for(int k = 0; k < this.image.getHeight(); k++)
            {
                Color cor = new Color(this.image.getRGB(i, k));
            }

    }

        /*
     * Calcula frequencia dos 256 tons de cinza
     */
    private void calculaFrequencia() {

        //armazena o valor de cada variavel no histograma
        for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {
                Color cor = new Color(this.image.getRGB(x, y));
                this.frequencia[cor.getRed()][0] += 1;
                this.frequencia[cor.getGreen()][1] += 1;
                this.frequencia[cor.getBlue()][2] += 1;
            }//fim do for
        }
    }//fim do calculaFrequencia

}
