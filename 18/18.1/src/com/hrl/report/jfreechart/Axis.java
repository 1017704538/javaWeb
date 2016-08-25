package com.hrl.report.jfreechart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.lowagie.text.Font;

public class Axis {
    private String fileName = null; // 临时文件名
    
    /**
     * 构造方法
     * @throws IOException
     */
    public Axis() throws IOException {
        JFreeChart chart = ChartFactory.createBarChart3D("全国主要城市房价对比图", // 标题
                "城市", // x轴显示的标签
                "房价", // y抽显示的标签
                getCategoryDataset(), // 数据集
                PlotOrientation.VERTICAL, // 图标方向为垂直方向
                false, // 是否显示图例
                false, // 是否生成工具
                false); // 是否生成url链接
        chart.getTitle().setFont(new java.awt.Font("黑体", Font.ITALIC, 22)); // 设置标题字体样式
        CategoryAxis x = chart.getCategoryPlot().getDomainAxis();// 取得横轴
        x.setLabelFont(new java.awt.Font("黑体", Font.ITALIC, 12)); 
        x.setTickLabelFont(new java.awt.Font("黑体", Font.ITALIC, 12));
        x.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//分类标签以45度角倾斜
        NumberAxis y = (NumberAxis) chart.getCategoryPlot().getRangeAxis(); // 取得纵轴
        y.setLabelFont(new java.awt.Font("黑体", Font.ITALIC, 12));
        StandardEntityCollection standardEntityCollection = new StandardEntityCollection();
        ChartRenderingInfo info = new ChartRenderingInfo(
                standardEntityCollection);
        File file = new File("temp.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file);
        fileName = ServletUtilities.saveChartAsPNG(chart, 600, 400, info, null);
        ChartUtilities.writeImageMap(out, "map", info, false);
        out.close();
    }
    
    /**
     * 获得数据集
     * 
     * @return
     */
    public CategoryDataset getCategoryDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10000, "", "北京");
        dataset.addValue(7700, "", "上海");
        dataset.addValue(5700, "", "广州");
        dataset.addValue(4200, "", "杭州");
        dataset.addValue(3700, "", "苏州");
        dataset.addValue(4300, "", "长春");
        return dataset;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
