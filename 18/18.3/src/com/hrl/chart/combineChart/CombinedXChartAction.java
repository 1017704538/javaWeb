package com.hrl.chart.combineChart;

import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;

public class CombinedXChartAction extends ActionSupport {
    private JFreeChart chart = null;// struts2的固定写法
    
    private String in_s1 = "自己挣钱";
    private String in_s2 = "家庭来源";
    
    private String out_s1 = "学习用品";
    private String out_s2 = "生活用品";
    private String out_s3 = "其他";
    
    private String student1 = "吉姆";
    private String student2 = "韩梅梅";
    private String student3 = "汤姆";
    private String student4 = "爱丽丝";
    
    public JFreeChart getChart() {
        // 绘制第一张图（收入报表）
        NumberAxis numberAxis_in = new NumberAxis("学生经济收入");
        numberAxis_in.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer lineAndShapeRenderer = new LineAndShapeRenderer();
        lineAndShapeRenderer
                .setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot categoryPlot_in = new CategoryPlot(
                getCategoryDataset_in(), null, numberAxis_in,
                lineAndShapeRenderer);
        categoryPlot_in.setDomainGridlinesVisible(true);
        
        // 绘制第二章报表（支出报表）
        NumberAxis numberAxis_out = new NumberAxis("学生经济来源");
        numberAxis_out
                .setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer barRenderer = new BarRenderer();
        barRenderer
                .setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot categoryPlot_out = new CategoryPlot(
                getCategoryDataset_out(), null, numberAxis_out, barRenderer);
        categoryPlot_out.setDomainGridlinesVisible(true);
        
        // 将两张报表合二为一
        CategoryAxis categoryAxis = new CategoryAxis("学生");
        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot(
                categoryAxis);
        combinedPlot.add(categoryPlot_in, 2);
        combinedPlot.add(categoryPlot_out, 2);
        chart = new JFreeChart("某学校学生零用钱收支情况对比图", new Font("黑体", 1, 12),
                combinedPlot, true);
        return chart;
    }
    
    /**
     * 获得第一张对比报表的数据源（收入）
     * 
     * @return
     */
    private DefaultCategoryDataset getCategoryDataset_in() {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        // 自己挣钱
        categoryDataset.addValue(1800, in_s1, student1);
        categoryDataset.addValue(3800, in_s1, student2);
        categoryDataset.addValue(2500, in_s1, student3);
        categoryDataset.addValue(2000, in_s1, student4);
        // 家庭来源
        categoryDataset.addValue(4000, in_s2, student1);
        categoryDataset.addValue(1000, in_s2, student2);
        categoryDataset.addValue(2500, in_s2, student3);
        categoryDataset.addValue(3000, in_s2, student4);
        return categoryDataset;
    }
    
    /**
     * 获得第一张对比报表的数据源（支出）
     * 
     * @return
     */
    private DefaultCategoryDataset getCategoryDataset_out() {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        // 学习用品
        categoryDataset.addValue(800, out_s1, student1);
        categoryDataset.addValue(800, out_s1, student2);
        categoryDataset.addValue(500, out_s1, student3);
        categoryDataset.addValue(200, out_s1, student4);
        // 生活用品
        categoryDataset.addValue(900, out_s2, student1);
        categoryDataset.addValue(1000, out_s2, student2);
        categoryDataset.addValue(1500, out_s2, student3);
        categoryDataset.addValue(1100, out_s2, student4);
        // 其他
        categoryDataset.addValue(600, out_s3, student1);
        categoryDataset.addValue(200, out_s3, student2);
        categoryDataset.addValue(450, out_s3, student3);
        categoryDataset.addValue(860, out_s3, student4);
        return categoryDataset;
    }
}
