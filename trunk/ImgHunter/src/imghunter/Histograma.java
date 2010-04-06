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
    private double[] frequencia = new double[256];
    private BufferedImage foto;

    public Histograma(BufferedImage cinza) {
        this.foto = cinza;
        this.grafico = this.criaChart();
    }//fim do construtor

    public JFreeChart getChart() {
        return this.grafico;
    }

    /*
     * Calcula frequencia dos 256 tons de cinza
     */
    private void calculaFrequencia() {

        for (int x = 0; x < this.foto.getWidth(); x++) {
            for (int y = 0; y < this.foto.getHeight(); y++) {
                Color cor = new Color(this.foto.getRGB(x, y));
                this.frequencia[cor.getRed()] += 1;
            }//fim do for
        }
    }//fim do calculaFrequencia

    private JFreeChart criaChart() {
        this.calculaFrequencia();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int x = 0; x < this.frequencia.length; x++) {
            dataset.addValue(frequencia[x], "", "" + x);
        }

        JFreeChart chart = ChartFactory.createBarChart(
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
}//fim da classe

