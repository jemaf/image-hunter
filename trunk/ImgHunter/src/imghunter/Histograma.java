/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author eduardo
 */
public class Histograma {

    private JFreeChart grafico;
    private double[][] frequencia = new double[256][3];
    private BufferedImage foto;

    public Histograma(BufferedImage img) {
        this.foto = img;
        this.grafico = this.criaChart();
    }//fim do construtor

    public JFreeChart getChart() {
        return this.grafico;
    }

    /*
     * Calcula frequencia dos 256 tons de cinza
     */
    private void calculaFrequencia() {

        //armazena o valor de cada variavel no histograma
        for (int x = 0; x < this.foto.getWidth(); x++) {
            for (int y = 0; y < this.foto.getHeight(); y++) {
                Color cor = new Color(this.foto.getRGB(x, y));
                this.frequencia[cor.getRed()][0] += 1;
                this.frequencia[cor.getGreen()][1] += 1;
                this.frequencia[cor.getBlue()][2] += 1;
            }//fim do for
        }
    }//fim do calculaFrequencia

    private JFreeChart criaChart() {
        this.calculaFrequencia();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //insere os valores no dataset para carregar no grafico
        for (int x = 0; x < this.frequencia.length; x++) {
            String v2 = String.valueOf(x);
           // String v2 = x == 0 || 16 % x == 0? String.valueOf(x) : "";
            dataset.addValue(frequencia[x][0], "R", v2);
            dataset.addValue(frequencia[x][1], "G", v2);
            dataset.addValue(frequencia[x][2], "B", v2);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Histograma", // chart title
                "Pixels", // domain axis label
                "Quantidade", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );
        return chart;
    }//fim do criaChart

    /**
     * Equaliza o histograma
     */
    public void equalizarHistograma() {
        //TODO
    }

}//fim da classe

