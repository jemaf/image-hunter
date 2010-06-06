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
              for(int i = larg - maskSize/2 ; i< larg + maskSize/2; i++)
                  for(int j = alt - maskSize/2; j< alt + maskSize/2 ; j++)
                          if(i >= 0 && i < img.getWidth())
                              if(j >= 0 && j < img.getHeight())
                                  if(i != larg && j != alt)
                                   mascara.add(img.getRGB(larg,alt));

            // ordena a mascara  
            Collections.sort(mascara);
           // pega a cor
           // uma lista pra cada R_G_B
           ArrayList rList = null, gList = null, bList = null;
           for(int cores = 0; cores < mascara.size(); cores ++){
               Color atual = new Color((Integer) mascara.get(cores));
               rList.add(atual.getRed());
               gList.add(atual.getGreen());
               bList.add(atual.getBlue());
           }
            // grava no arquivo novo
           imgOut.setRGB(larg, alt,(Integer) mascara.get(mascara.size()-1));

           // limpa pro próximo pixel
           mascara.clear();
                  }
              }
        ImageIO.write(imgOut, "JPG", new File("_MAX"+name));

          }


public void binarizacao(BufferedImage img, int limiar, String name) throws IOException
{
int larg, alt;
BufferedImage imgOut = new BufferedImage(
               img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);
for(larg = 0; larg< img.getWidth(); larg++)
{       for(alt = 0; alt< img.getHeight(); alt++)
            {
              int  aux = img.getRGB(larg, alt);
              if(aux< limiar)
                imgOut.setRGB(larg, alt, Color.BLACK.getRGB());
              else imgOut.setRGB(larg, alt, Color.WHITE.getRGB());

            }
        }

ImageIO.write(imgOut, "JPG", new File("BIN_"+name));


}

}

