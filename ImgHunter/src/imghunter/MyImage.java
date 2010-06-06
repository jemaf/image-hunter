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
    private String imgOriginal;
    private int[][] freqRGB;
    private int[][] freqYUV;
    private int[][] freqHSV;

    public MyImage(String imgOriginal) throws IOException {

        this.imgOriginal = imgOriginal;
        image = ImageIO.read(new File(imgOriginal));
        this.freqRGB = new int[256][3];
        this.freqHSV = new int[image.getWidth() * image.getHeight()][3];
        this.freqYUV = new int[image.getWidth() * image.getHeight()][3];
        this.calculaFrequencia();

    }
   public void  testaFiltro() throws IOException{

   GerarTonsDeCinza();
   Filtros.binarizacao(image, 177, imgOriginal);
   Filtros.filtroMaximo(image, 3, imgOriginal);
   Filtros.filtroMedia(image, 5, imgOriginal);
   Filtros.filtroMinimo(image, 5, imgOriginal);
   Filtros.filtroMediana(image, 5, imgOriginal);

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
        return this.freqRGB;
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
        for (int i = 0; i < freqRGB.length; i++) {

            freqA[i][0] += i == 0 ? this.freqRGB[i][0]
                    : this.freqRGB[i][0] + freqA[i - 1][0];
            freqA[i][1] += i == 0 ? this.freqRGB[i][1]
                    : this.freqRGB[i][1] + freqA[i - 1][1];
            freqA[i][2] += i == 0 ? this.freqRGB[i][2]
                    : this.freqRGB[i][2] + freqA[i - 1][2];
        }

        //define numero de pixels para cada cor
        int npR = freqA[freqA.length][0], npG = freqA[freqA.length][1],
                npB = freqA[freqA.length][2];

        //divide a frequencia pelo total d pixels e multiplica pelo numero de tons
        for (int i = 0; i < freqRGB.length; i++) {
            freqA[i][0] = (int) (((double) freqA[i][0] / (double) (npR)) * (double) this.freqRGB.length);
            freqA[i][1] = (int) (((double) freqA[i][1] / (double) (npG)) * (double) this.freqRGB.length);
            freqA[i][2] = (int) (((double) freqA[i][2] / (double) (npB)) * (double) this.freqRGB.length);
        }

        //gera nova frequencia
        for (int i = 0; i < freqEq.length; i++) {

            int newValR = 0;
            int newValG = 0;
            int newValB = 0;

            for (int k = 0; k < freqA.length; k++) {
                if (freqA[k][0] == i) {
                    newValR += this.freqRGB[k][0];
                }
                if (freqA[k][1] == i) {
                    newValG += this.freqRGB[k][1];
                }
                if (freqA[k][2] == i) {
                    newValB += this.freqRGB[k][2];
                }
            }

            freqEq[i][0] = newValR;
            freqEq[i][1] = newValG;
            freqEq[i][2] = newValB;

        }

        this.freqRGB = freqEq;

        //aplica novos tons a imagem
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int k = 0; k < this.image.getHeight(); k++) {
                Color cor = new Color(this.image.getRGB(i, k));
                int r = this.freqRGB[cor.getRed()][0];
                int g = this.freqRGB[cor.getGreen()][1];
                int b = this.freqRGB[cor.getBlue()][2];

                Color newColor = new Color(r, g, b);
                this.image.setRGB(i, k, newColor.getRGB());
            }
        }
    }

    /**
     * Calcula amostragem da imagem
     */
    public int[] amostragem() {

   int tamanhoVetor = this.image.getWidth() * this.image.getHeight();
   // se a imagem não for multipla de 8  "arredondar " o vetor
   tamanhoVetor += 8 - (tamanhoVetor % 8);
   // reduz o tamanho da representação em 8 vezes. Amostragem de 8.
   tamanhoVetor /= 8;
   int amostragem[] = new int[tamanhoVetor];
   // desencargo de conciência :) pra garantir todas as posições
   amostragem[tamanhoVetor-1] = 0;
   amostragem[tamanhoVetor-2] = 0;
   amostragem[tamanhoVetor-3] = 0;
   amostragem[tamanhoVetor-4] = 0;
   amostragem[tamanhoVetor-5] = 0;
   amostragem[tamanhoVetor-6] = 0;
   amostragem[tamanhoVetor-7] = 0;

   // percorrer imagem e calcular a média a cada 8 pixels
   int media = 0, cont = 0 ;
   for (int i = 0; i < this.image.getWidth(); i++) {
            for (int k = 0; k < this.image.getHeight(); k++) {
                cont ++;
                media += this.image.getRGB(i, k);
                // a cada 8 pixels contabiliza a média(arredondada pra int)
                if(cont==8)
                {  amostragem[i+k] = (media/8);
                   // reseta controladores
                   cont  = 0;
                   media = 0;
                    }
                }
        }

        return amostragem;
    }


    /*
     * Calcula frequencia dos 256 tons de cinza
     */
    private void calculaFrequencia() {

        int indice = 0;

        //armazena o valor de cada variavel no histograma
        for (int j = 0; j < this.image.getWidth(); j++) {
            for (int k = 0; k < this.image.getHeight(); k++, indice++) {
                Color cor = new Color(this.image.getRGB(j, k));

                int r = cor.getRed();
                int g = cor.getGreen();
                int b = cor.getBlue();

                this.freqRGB[r][0] += 1;
                this.freqRGB[g][1] += 1;
                this.freqRGB[b][2] += 1;

                this.rgbParaHsv(r, g, b, freqHSV[indice]);
                this.rgbParaYuv(r, g, b, this.freqYUV[indice]);


            }//fim do for
        }
    }//fim do calculaFrequencia

    /*
     * Calcula frequencia dos 256 tons de cinza
     */
    private void rgbParaYuv(int r, int g, int b, int yuv[]) {

        int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        int u = (int) ((b - y) * 0.492f);
        int v = (int) ((r - y) * 0.877f);

        yuv[0] = y;
        yuv[1] = u;
        yuv[2] = v;

//        System.out.println(yuv[0] + "," + yuv[1] + "," + yuv[2]);
    }

    private void rgbParaHsv(int r, int g, int b, int hsv[]) {

        int min;    //Valor mínimo de RGB
        int max;    //Valor máximo de RGB
        int delMax; //Variação de RGB

        if (r > g) {
            min = g;
            max = r;
        } else {
            min = r;
            max = g;
        }
        if (b > max) {
            max = b;
        }
        if (b < min) {
            min = b;
        }

        delMax = max - min;

        float H = 0, S;
        float V = max;

        if (delMax == 0) {
            H = 0;
            S = 0;
        } else {
            S = delMax / 255f;
            if (r == max) {
                H = ((g - b) / (float) delMax) * 60;
            } else if (g == max) {
                H = (2 + (b - r) / (float) delMax) * 60;
            } else if (b == max) {
                H = (4 + (r - g) / (float) delMax) * 60;
            }
        }

        hsv[0] = (int) (H);
        hsv[1] = (int) (S * 100);
        hsv[2] = (int) (V * 100);

        //       System.out.println(hsv[0] + "," + hsv[1] + "," + hsv[2]);
    }

    @Override
    public String toString() {

        String s = imgOriginal + " ";

        String rgb = "RGB:";
        String hsv = "HSV:";
        String yuv = "YUV:";
        String amostragem = "AMOSTRAGEM:";

        for (int i = 0; i < this.freqRGB.length; i++) {
            rgb += String.format("%d,%d,%d", freqRGB[i][0], freqRGB[i][1], freqRGB[i][2]);
            hsv += String.format("%d,%d,%d", freqHSV[i][0], freqHSV[i][1], freqHSV[i][2]);
            yuv += String.format("%d,%d,%d", freqYUV[i][0], freqYUV[i][1], freqYUV[i][2]);
            if (i < freqRGB.length) {
                rgb += "-";
                hsv += "-";
                yuv += "-";
            }
        }

        for (int i = this.freqRGB.length; i < this.freqHSV.length; i++) {
            hsv += String.format("%d,%d,%d", freqHSV[i][0], freqHSV[i][1], freqHSV[i][2]);
            yuv += String.format("%d,%d,%d", freqYUV[i][0], freqYUV[i][1], freqYUV[i][2]);
            if (i < freqRGB.length) {
                hsv += "-";
                yuv += "-";
            }
        }

        int[] vetor = this.amostragem();

        for(int i = 0; i < vetor.length; i++) {

            amostragem += vetor[i];
            if(i < vetor.length - 1) amostragem += ",";
        }

        s = rgb + " " + hsv + " " + yuv + " " + amostragem;

        return s;
    }

    public static void main(String args[]) {


        int r = 0;
        int b = 255;
        int g = 0;

        int y = (int) ((0.2990 * r) + (0.5870 * g) + (0.1140 * b));
        int u = (int) ((-0.1687 * r) + (-0.3313 * g) + (0.5000 * b));
        int v = (int) ((0.5000 * r) + (-0.4187 * g) + (-0.0813 * b));


        System.out.println(y + "," + u + "," + v);

    }
}
