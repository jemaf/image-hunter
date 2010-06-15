/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

/**
 * Classe que implementa os filtros da terceita parte do trablho de PID
 * @author Bonfim
 */
public class Filtros {

    // se o mask size for par pode dar merda
    /** faz o filtro máximo
     */
    public static BufferedImage filtroMaximo(BufferedImage img, int maskSize,
            String name) throws IOException {

        BufferedImage imgOutMax = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int alt, larg;

        ArrayList mascaraR = new ArrayList<Integer>();
        ArrayList mascaraG = new ArrayList<Integer>();
        ArrayList mascaraB = new ArrayList<Integer>();


        //perrcorrer a imagem;
        for (larg = 0; larg < img.getWidth(); larg++) {
            for (alt = 0; alt < img.getHeight(); alt++) {
                // encher a mascara
                for (int i = larg - maskSize / 2; i <= larg + maskSize / 2; i++) {
                    for (int j = alt - maskSize / 2; j <= alt + maskSize / 2; j++) {
                        if (i >= 0 && i < img.getWidth()) {
                            if (j >= 0 && j < img.getHeight()) {
                                mascaraR.add(new Color(img.getRGB(i, j)).getRed());
                                mascaraG.add(new Color(img.getRGB(i, j)).getGreen());
                                mascaraB.add(new Color(img.getRGB(i, j)).getBlue());

                            }
                        }
                    }
                }
                // ordena a mascara
                Collections.sort(mascaraR);
                Collections.sort(mascaraG);
                Collections.sort(mascaraB);

                // grava no arquivo novo
                Color c = new Color((Integer) mascaraR.get(mascaraR.size() - 1),
                        (Integer) mascaraG.get(mascaraG.size() - 1), (Integer) mascaraB.get(mascaraB.size() - 1));
                imgOutMax.setRGB(larg, alt, c.getRGB());

                // limpa pro próximo pixel
                mascaraR.clear();
                mascaraG.clear();
                mascaraB.clear();
            }
        }
        ImageIO.write(imgOutMax, "JPG", new File("_MAX" + name));

        return imgOutMax;
    }

    public static BufferedImage binarizacao(BufferedImage img, int limiar, String name) throws IOException {

        int lut[] = new int[256];

        for (int i = 0; i < lut.length; i++) {
            lut[i] = i < limiar ? 0 : 255;
        }

        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < imgOut.getWidth(); x++) {
            for (int y = 0; y < imgOut.getHeight(); y++) {

                Color c = new Color(img.getRGB(x, y));
                int newColorR = lut[c.getRed()];
                int newColorG = lut[c.getGreen()];
                int newColorB = lut[c.getBlue()];
                c = new Color(newColorR, newColorG, newColorB);
                imgOut.setRGB(x, y, c.getRGB());

            }
        }

        ImageIO.write(imgOut, "JPG", new File("BIN_" + name));

        return imgOut;
    }

    public static BufferedImage filtroMinimo(BufferedImage img, int maskSize,
            String name) throws IOException {

        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int alt, larg;
        ArrayList mascaraR = new ArrayList<Integer>();
        ArrayList mascaraG = new ArrayList<Integer>();
        ArrayList mascaraB = new ArrayList<Integer>();


        //perrcorrer a imagem;
        for (larg = 0; larg < img.getWidth(); larg++) {
            for (alt = 0; alt < img.getHeight(); alt++) {
                // encher a mascara
                for (int i = larg - maskSize / 2; i <= larg + maskSize / 2; i++) {
                    for (int j = alt - maskSize / 2; j <= alt + maskSize / 2; j++) {
                        if (i >= 0 && i < img.getWidth()) {
                            if (j >= 0 && j < img.getHeight()) {
                                // if(i != larg && j != alt){
                                mascaraR.add(new Color(img.getRGB(i, j)).getRed());
                                mascaraG.add(new Color(img.getRGB(i, j)).getGreen());
                                mascaraB.add(new Color(img.getRGB(i, j)).getBlue());
                                //   }
                            }
                        }
                    }
                }
                // ordena a mascara
                Collections.sort(mascaraR);
                Collections.sort(mascaraG);
                Collections.sort(mascaraB);

                // grava no arquivo novo
                Color c = new Color((Integer) mascaraR.get(0),
                        (Integer) mascaraG.get(0), (Integer) mascaraB.get(0));
                imgOut.setRGB(larg, alt, c.getRGB());

                // limpa pro próximo pixel
                mascaraR.clear();
                mascaraG.clear();
                mascaraB.clear();
            }
        }
        ImageIO.write(imgOut, "JPG", new File("_MIN" + name));

        return imgOut;
    }

    public static BufferedImage filtroMediana(BufferedImage img, int maskSize,
            String name) throws IOException {

        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int alt, larg;

        ArrayList mascaraR = new ArrayList<Integer>();
        ArrayList mascaraG = new ArrayList<Integer>();
        ArrayList mascaraB = new ArrayList<Integer>();


        //perrcorrer a imagem;
        for (larg = 0; larg < img.getWidth(); larg++) {
            for (alt = 0; alt < img.getHeight(); alt++) {
                // encher a mascara
                for (int i = larg - maskSize / 2; i <= larg + maskSize / 2; i++) {
                    for (int j = alt - maskSize / 2; j <= alt + maskSize / 2; j++) {
                        if (i >= 0 && i < img.getWidth()) {
                            if (j >= 0 && j < img.getHeight()) {
                                mascaraR.add(new Color(img.getRGB(i, j)).getRed());
                                mascaraG.add(new Color(img.getRGB(i, j)).getGreen());
                                mascaraB.add(new Color(img.getRGB(i, j)).getBlue());

                            }
                        }
                    }
                }
                // ordena a mascara
                Collections.sort(mascaraR);
                Collections.sort(mascaraG);
                Collections.sort(mascaraB);

                // grava no arquivo novo
                Color c = new Color((Integer) mascaraR.get(mascaraR.size() / 2),
                        (Integer) mascaraG.get(mascaraG.size() / 2), (Integer) mascaraB.get(mascaraB.size() / 2));
                imgOut.setRGB(larg, alt, c.getRGB());

                // limpa pro próximo pixel
                mascaraR.clear();
                mascaraG.clear();
                mascaraB.clear();
            }
        }
        ImageIO.write(imgOut, "JPG", new File("_Mediana" + name));

        return imgOut;
    }

    public static BufferedImage filtroMedia(BufferedImage img, int maskSize,
            String name) throws IOException {

        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int alt, larg;
        ArrayList mascara = new ArrayList<Integer>();


        //perrcorrer a imagem;
        for (larg = 0; larg < img.getWidth(); larg++) {
            for (alt = 0; alt < img.getHeight(); alt++) {
                // encher a mascara
                for (int i = larg - maskSize / 2; i <= larg + maskSize / 2; i++) {
                    for (int j = alt - maskSize / 2; j <= alt + maskSize / 2; j++) {
                        if (i >= 0 && i < img.getWidth()) {
                            if (j >= 0 && j < img.getHeight()) {
                                mascara.add(new Color(img.getRGB(i, j)));

                            }
                        }
                    }
                }
                // descobrir o valor de NC médio
                int mediaR = 0;
                int mediaG = 0;
                int mediaB = 0;
                for (int t = 0; t < mascara.size(); t++) {
                    Color a = (Color) mascara.get(t);
                    mediaR += a.getRed();
                    mediaG += a.getGreen();
                    mediaB += a.getBlue();
                }
                mediaR /= mascara.size();
                mediaG /= mascara.size();
                mediaB /= mascara.size();

                // grava no arquivo novo
                Color c = new Color(mediaR, mediaG, mediaB);
                imgOut.setRGB(larg, alt, c.getRGB());

                // limpa pro próximo pixel
                mascara.clear();
            }
        }
        ImageIO.write(imgOut, "JPG", new File("_Med" + name));

        return imgOut;
    }


    /*detectores de borda*/
    public static BufferedImage bordaSobel(BufferedImage img, String name) throws IOException {
        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[] img1d = umaDimensao(img);
        float[] template = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
        float[] GY = new float[img.getWidth() * img.getHeight()];
        float[] GX = new float[img.getWidth() * img.getHeight()];
        int[] total = new int[img.getWidth() * img.getHeight()];
        int[] output = new int[img.getWidth() * img.getHeight()];

        int sum = 0;
        int max = 0;

        for (int x = 1; x < img.getWidth() - 2; x++) {
            for (int y = 1; y < img.getHeight() - 2; y++) {
                sum = 0;

                for (int x1 = 0; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 3; y1++) {
                        int x2 = (x - 1 + x1);
                        int y2 = (y - 1 + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[y1 * 3 + x1]);
                        sum += value;
                    }
                }
                GY[y * img.getWidth() + x] = sum;
                for (int x1 = 0; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 3; y1++) {
                        int x2 = (x - 1 + x1);
                        int y2 = (y - 1 + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[x1 * 3 + y1]);
                        sum += value;
                    }
                }
                GX[y * img.getWidth() + x] = sum;

            }
        }
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                total[y * img.getWidth() + x] = (int) Math.sqrt(GX[y * img.getWidth() + x] * GX[y * img.getWidth() + x] + GY[y * img.getWidth() + x] * GY[y * img.getWidth() + x]);
                if (max < total[y * img.getWidth() + x]) {
                    max = total[y * img.getWidth() + x];
                }
            }
        }
        float ratio = (float) max / 255;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                sum = (int) (total[y * img.getWidth() + x] / ratio);
                output[y * img.getWidth() + x] = 0xff000000 | ((int) sum << 16 | (int) sum << 8 | (int) sum);
            }
        }

        imgOut = duasDimensoes(imgOut, output);
        ImageIO.write(imgOut, "JPG", new File("_SOBEL" + name));
        return imgOut;
    }

    public static int[] umaDimensao(BufferedImage img) {

        int[] imagem = new int[img.getWidth() * img.getHeight()];
        int cont = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                imagem[cont] = img.getRGB(i, j);
                cont++;
            }
        }
        return imagem;
    }

    public static BufferedImage duasDimensoes(BufferedImage out, int[] imagem) {
        int cont = 0;
        for (int i = 0; i < out.getWidth(); i++) {
            for (int j = 0; j < out.getHeight(); j++) {
                out.setRGB(i, j, imagem[cont]);
                cont++;
            }
        }
        return out;
    }

    public static BufferedImage bordaPrewitt(BufferedImage img, String name) throws IOException {
        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[] img1d = umaDimensao(img);
        float[] template = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        float[] GY = new float[img.getWidth() * img.getHeight()];
        float[] GX = new float[img.getWidth() * img.getHeight()];
        int[] total = new int[img.getWidth() * img.getHeight()];
        int[] output = new int[img.getWidth() * img.getHeight()];

        int sum = 0;
        int max = 0;

        for (int x = 1; x < img.getWidth() - 2; x++) {
            for (int y = 1; y < img.getHeight() - 2; y++) {
                sum = 0;

                for (int x1 = 0; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 3; y1++) {
                        int x2 = (x - 1 + x1);
                        int y2 = (y - 1 + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[y1 * 3 + x1]);
                        sum += value;
                    }
                }
                GY[y * img.getWidth() + x] = sum;
                for (int x1 = 0; x1 < 3; x1++) {
                    for (int y1 = 0; y1 < 3; y1++) {
                        int x2 = (x - 1 + x1);
                        int y2 = (y - 1 + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[x1 * 3 + y1]);
                        sum += value;
                    }
                }
                GX[y * img.getWidth() + x] = sum;

            }
        }
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                total[y * img.getWidth() + x] = (int) Math.sqrt(GX[y * img.getWidth() + x] * GX[y * img.getWidth() + x] + GY[y * img.getWidth() + x] * GY[y * img.getWidth() + x]);
                if (max < total[y * img.getWidth() + x]) {
                    max = total[y * img.getWidth() + x];
                }
            }
        }
        float ratio = (float) max / 255;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                sum = (int) (total[y * img.getWidth() + x] / ratio);
                output[y * img.getWidth() + x] = 0xff000000 | ((int) sum << 16 | (int) sum << 8 | (int) sum);
            }
        }

        imgOut = duasDimensoes(imgOut, output);
        ImageIO.write(imgOut, "JPG", new File("_PREWWIT" + name));
        return imgOut;
    }



     public static BufferedImage bordaRobert(BufferedImage img, String name) throws IOException {
        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[] img1d = umaDimensao(img);
        float[] template = {-1, 0, 0,1};
        float[] GY = new float[img.getWidth() * img.getHeight()];
        float[] GX = new float[img.getWidth() * img.getHeight()];
        int[] total = new int[img.getWidth() * img.getHeight()];
        int[] output = new int[img.getWidth() * img.getHeight()];

        int sum = 0;
        int max = 0;

        for (int x = 0; x < img.getWidth() - 1; x++) {
            for (int y = 0; y < img.getHeight() - 1; y++) {
                sum = 0;

                for (int x1 = 0; x1 < 2; x1++) {
                    for (int y1 = 0; y1 < 2; y1++) {
                        int x2 = (x  + x1);
                        int y2 = (y  + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[y1 * 2 + x1]);
                        sum += value;
                    }
                }
                GY[y * img.getWidth() + x] = sum;
                for (int x1 = 0; x1 < 2; x1++) {
                    for (int y1 = 0; y1 < 2; y1++) {
                        int x2 = (x + x1);
                        int y2 = (y + y1);
                        float value = (img1d[y2 * img.getWidth() + x2] & 0xff) * (template[x1 * 2 + y1]);
                        sum += value;
                    }
                }
                GX[y * img.getWidth() + x] = sum;

            }
        }
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                total[y * img.getWidth() + x] = (int) Math.sqrt(GX[y * img.getWidth() + x] * GX[y * img.getWidth() + x] + GY[y * img.getWidth() + x] * GY[y * img.getWidth() + x]);
                if (max < total[y * img.getWidth() + x]) {
                    max = total[y * img.getWidth() + x];
                }
            }
        }
        float ratio = (float) max / 255;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                sum = (int) (total[y * img.getWidth() + x] / ratio);
                output[y * img.getWidth() + x] = 0xff000000 | ((int) sum << 16 | (int) sum << 8 | (int) sum);
            }
        }

        imgOut = duasDimensoes(imgOut, output);
        ImageIO.write(imgOut, "JPG", new File("_ROBERT" + name));
        return imgOut;
    }
}



