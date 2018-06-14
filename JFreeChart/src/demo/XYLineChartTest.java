package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class XYLineChartTest {

    /**
     * 生成折线图
     * @param chartTitle 图片标题
     * @param xTitle x轴标题
     * @param yTitle y轴标题
     * @param xyDataset 数据集
     */
    public JFreeChart makeLineAndShapeChart(String chartTitle, String xTitle, String yTitle, XYDataset xyDataset){
        //构建一个chart
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xTitle, yTitle, xyDataset, PlotOrientation.VERTICAL, true, false, false);
        chart.setTextAntiAlias(false);
        //设置背景颜色
        chart.setBackgroundPaint(Color.WHITE);
        //配置字体
        // X轴字体
        Font xfont = new Font("宋体",Font.TRUETYPE_FONT,12	) ;
        // Y轴字体
        Font yfont = new Font("SansSerif",Font.TRUETYPE_FONT,12);
        //图例标题字体
        Font legendFont = new Font("宋体",Font.PLAIN,12) ;
        // 图片标题
        Font titleFont = new Font("宋体", Font.BOLD , 25) ;

        //设置图标题title的字体
        chart.getTitle().setFont(titleFont);

        //设置图例的字体
        chart.getLegend().setItemFont(legendFont);
        //RectangleEdge rectangleEdge = new RectangleEdge("adf");

        //chart.getLegend().setPosition(position);


        //图形的绘制结构对x象
        XYPlot xyPlot = (XYPlot) chart.getPlot();

        //xy轴位置（左下改上右）
//		xyPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
//		xyPlot.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);

        // x轴 分类轴网格是否可见
        xyPlot.setDomainGridlinesVisible(false);
        // y轴  数据轴网格是否可见
        xyPlot.setRangeGridlinesVisible(true);
        // 虚线色彩
        xyPlot.setRangeGridlinePaint(Color.DARK_GRAY);
        // 虚线色彩
        xyPlot.setDomainGridlinePaint(Color.WHITE);
        //折线图的背景颜色
        xyPlot.setBackgroundPaint(Color.WHITE);
        // 设置轴和面板之间的距离
        xyPlot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 0D));

        /********************** 横轴 x *****************/
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        // 轴标题
        domainAxis.setLabelFont(xfont);
        // 轴数值
        domainAxis.setTickLabelFont(xfont);
        //domainAxis.setLabelPaint(Color.BLUE);//轴标题的颜色
        //domainAxis.setTickLabelPaint(Color.BLUE);//轴数值的颜色

        //横轴 lable 的位置   横轴上的 Lable 45度倾斜 DOWN_45
//		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        // 设置距离图片左端距离
        domainAxis.setLowerMargin(0.0);
        // 设置距离图片右端距离
        domainAxis.setUpperMargin(0.0);

        /********************** 纵轴 y *****************/
        NumberAxis numberaxis = (NumberAxis) xyPlot.getRangeAxis();//ValueAxis
        numberaxis.setLabelFont(yfont);
        numberaxis.setTickLabelFont(yfont);
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRangeIncludesZero(true);
        numberaxis.setInverted(false);

        // 获得renderer
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyPlot.getRenderer();
        renderer.setBaseShapesVisible(false);
        renderer.setBaseLinesVisible(true);

        // 显示折点数据
//		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		lineandshaperenderer.setBaseItemLabelsVisible(true);

        return chart;
    }
    public static void main(String args[]){
        try {
            //BufferedImage img = new CreateJFreeChart().makeLineAndShapeChart("", "里程(km)", "TQI", createDataset()).createBufferedImage(640, 360);
            FileOutputStream fos = new FileOutputStream("c:/uploadFiles/abc.png");
            ChartUtilities.writeChartAsPNG(fos, new XYLineChartTest().makeLineAndShapeChart("", "里程(km)", "TQI", createDataset()), 640, 360);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static XYDataset createDataset( )
    {
        final XYSeries jan1 = new XYSeries( "1月上旬" );
        final XYSeries jan2 = new XYSeries( "1月下旬" );
        final XYSeries feb1 = new XYSeries( "2月上旬" );
        final XYSeries feb2 = new XYSeries( "2月下旬" );
        final XYSeries mar1 = new XYSeries( "3月上旬" );
        for (int i = 0; i < 1300; i++) {
            jan1.add(i, 4- 2 * Math.random());
            jan2.add(i, 4- 2 * Math.random());
            feb1.add(i, 4- 2 * Math.random());
            feb2.add(i, 4- 2 * Math.random());
            mar1.add(i, 4- 2 * Math.random());
        }

        jan1.addOrUpdate(650, 8);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries( jan1 );
        dataset.addSeries( jan2 );
        dataset.addSeries( feb1 );
        dataset.addSeries( feb2 );
        dataset.addSeries( mar1 );
        return dataset;
    }
}
