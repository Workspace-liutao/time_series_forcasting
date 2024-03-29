package com.edu.jnu.atm.samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class ResultGraph {
	
	private void showResult(List<Double> sourceList, List<Double> predictList, double RATE)
	{
		/**
		 * construct a new window to show the variation of sourceList and predictList
		 * RATE denotes error ratio
		 */
		JFrame frame = new JFrame();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height; 
	    frame.setBounds((width - 800) / 2, (height - 600) / 2, 800, 500);
		JPanel panel = new JPanel();
		JTextField text = new JTextField();			
		String r = String.valueOf(RATE);
	    text.setEditable(false);
	    text.setFont(new Font("宋体", Font.BOLD, 20));
		text.setText(  "预测准确度为：" + r );	
		panel.add(text);
		ChartPanel tsc = LineChart(sourceList, predictList);
		frame.add(tsc, BorderLayout.CENTER );
		frame.add(panel, BorderLayout.SOUTH);   
		frame.setVisible(true);
	}
	
	private ChartPanel LineChart(List<Double> sourceData, List<Double> predictData)
	{
		/**
		 * draw the line chart 
		 */
    	ChartPanel frame1;
        DefaultCategoryDataset xydataset = createDataset(sourceData, predictData);
        JFreeChart jfreechart = ChartFactory.createLineChart("Forcasting Results", "Date", "Cash Flow", xydataset, PlotOrientation.VERTICAL, true, false, false);
        CategoryPlot plot = jfreechart.getCategoryPlot();
        plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        plot.setBackgroundPaint(Color.white);        
        frame1 = new ChartPanel(jfreechart,true);
        CategoryAxis domainAxis = (CategoryAxis)plot.getDomainAxis();
        domainAxis.setCategoryMargin(11);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setSeriesPaint(0, Color.black);   
        renderer.setBaseShapesFilled(true);
        renderer.setBaseItemLabelsVisible(true);  
		return frame1;
    }
    
     private DefaultCategoryDataset createDataset(List<Double> sourceData, List<Double> predictData)
     { 
            DefaultCategoryDataset lineseries = new DefaultCategoryDataset();
            String title1 = "Actual Demand";
            String title2 = "Forcasted Demand";;
            for (int i = 0; i < sourceData.size(); i ++)
            {
            	    lineseries.addValue(sourceData.get(i),title1, String.valueOf(i));
            	    lineseries.addValue(predictData.get(i),title2, String.valueOf(i));
            }
            return lineseries;
     }   
}
