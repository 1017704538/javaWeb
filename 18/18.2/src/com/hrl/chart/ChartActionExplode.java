package com.hrl.chart;

import java.awt.Font;
import java.text.AttributedString;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ChartActionExplode extends ActionSupport {
    private JFreeChart chart = null; // struts2的固定写法
    
    public JFreeChart getChart() {
        chart = ChartFactory.createPieChart("IT行业市场占有率调查图", // 图标标题
                getDataset(), // 数据集
                true,// 是否显示图例
                false, // 是否显示工具提示
                false); // 是否生成url
        // 设置标题样式
        chart.getTitle().setFont(new Font("黑体", Font.ITALIC, 22));
        // 取得第一个图例
        LegendTitle legendTitle = chart.getLegend();
        legendTitle.setItemFont(new Font("宋体", Font.BOLD, 12)); // 设置图例样式
        // 得到圆盘上的plot对象
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setExplodePercent("企业、政府行业", 0.1D);//企业、政府行业"挖"出来10%
        plot.setCircular(true);//设置各项所占的百分比 
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}{2}",
                NumberFormat.getNumberInstance(), NumberFormat
                        .getPercentInstance())); // {0}表示行业名，{1}表示具体值，{2}表示所占的百分比
        plot.setLabelFont(new Font("宋体", Font.ITALIC, 12)); // 设置饼图样式
        plot.setBackgroundAlpha(0.8f); // 设置背景
        plot.setForegroundAlpha(0.4f); // 设置前景
        return chart;
    }
    
    /**
     * 获取数据集
     * 
     * @return
     */
    private DefaultPieDataset getDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("IT服务行业", 30);
        dataset.setValue("能源行业", 10);
        dataset.setValue("企业、政府行业", 30);
        dataset.setValue("信息安全行业", 10);
        dataset.setValue("其他行业", 20);
        return dataset;
    }
}
