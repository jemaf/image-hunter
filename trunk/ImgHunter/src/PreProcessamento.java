/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import imghunter.*;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PreProcessamento {

    public static void main(String args[]) throws IOException {

        System.out.println("Iniciando pre processamento");

        args = new String[]{"/home/eduardo/NetBeansProjects/png2/1","" +
                "/home/eduardo/NetBeansProjects/png2/2","" +
                "/home/eduardo/NetBeansProjects/png2/3"};

        String s = "";
        BufferedWriter writer;

        for (int i = 0; i < args.length; i++) {

            writer = new BufferedWriter(new FileWriter
                    (args[i].split("/")[args[i].split("/").length - 1] + ".txt"));
            System.out.println("Abrindo diretório " + args[i]);

            File f = new File(args[i]);
            File fs[] = f.listFiles();

            for (int k = 0; k < fs.length; k++) {

                System.out.println(" " + fs[k].getAbsolutePath());
                s = preProcessar(fs[k].getAbsolutePath());
                writer.write(s);
                if (k < args.length - 1) {
                    writer.write("\n");
                }
            }
            writer.close();
        }
        System.out.println("Pre processamento concluido com sucesso");
    }

    private static String preProcessar(String string) throws IOException {

        MyImage img = new MyImage(string);
        return img.toString();
    }
}
