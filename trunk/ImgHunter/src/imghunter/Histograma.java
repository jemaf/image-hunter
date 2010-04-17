/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imghunter;

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
    private int[][] frequencia = new int[256][3];
    private MyImage foto;

    public Histograma(MyImage img) {
        this.foto = img;
        this.grafico = this.criaChart();
    }//fim do construtor

    public JFreeChart getChart() {
        return this.grafico;
    }

    private JFreeChart criaChart() {
        this.frequencia = this.foto.getFrequencia();

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

}//fim da classe

