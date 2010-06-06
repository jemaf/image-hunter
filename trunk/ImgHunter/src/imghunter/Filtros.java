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
 private int oi;
    public Filtros(int x){this.oi = x;}
 // se o mask size for par pode dar merda
    public void filtroMaximo(BufferedImage img, int maskSize, String name) throws IOException{
     
      BufferedImage imgOut = new BufferedImage(
               img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);
       int alt, larg;
       ArrayList mascara = new ArrayList<Integer>();
       

       //perrcorrer a imagem;
      for(larg = 0; larg < img.getWidth(); larg++)
      {
          for(alt = 0; alt < img.getHeight(); alt++)
          { 
             // encher a mascara
              for(int i = larg - maskSize/2 ; i<= larg + maskSize/2; i++){
                  for(int j = alt - maskSize/2; j<= alt + maskSize/2 ; j++){
                          if(i >= 0 && i < img.getWidth()){
                              if(j >= 0 && j < img.getHeight()){
                                 // if(i != larg && j != alt){
                                    mascara.add( new Color (img.getRGB(i,j)).getRed());
                                  //   }
                                } 
                            }          
                        } 
                   }
            // ordena a mascara
            Collections.sort(mascara);
           
            // grava no arquivo novo
               Color c =  new Color((Integer)mascara.get(mascara.size()-1),
                       (Integer)mascara.get(mascara.size()-1),(Integer)mascara.get(mascara.size()-1));
           imgOut.setRGB(larg, alt,c.getRGB());

           // limpa pro próximo pixel
           mascara.clear();
                  }
              }
        ImageIO.write(imgOut, "JPG", new File("_MAX"+name));

          }


public void binarizacao(BufferedImage img, int limiar, String name) throws IOException
{

        int lut[] = new int[256];

        for(int i = 0; i < lut.length; i++)
           lut[i] = i < limiar ? 0: 255;

        BufferedImage imgOut = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < imgOut.getWidth(); x++) {
            for (int y = 0; y < imgOut.getHeight(); y++) {

                Color c = new Color(img.getRGB(x, y));
                int newColor = lut[c.getRed()];
                c = new Color(newColor, newColor, newColor);
                imgOut.setRGB(x, y, c.getRGB());

            }
        }

        ImageIO.write(imgOut, "JPG", new File("BIN_"+name));

    }

}



