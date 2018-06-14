package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateJFreeChart {

	/**
	 * 生成折线图
	 * @param chartTitle 图片标题
	 * @param xTitle x轴标题
	 * @param yTitle y轴标题
	 * @param xyDataset 数据集
	 */
	public JFreeChart makeLineAndShapeChart(String chartTitle, String xTitle, String yTitle, CategoryDataset  xyDataset){
		//构建一个chart
		JFreeChart chart = ChartFactory.createLineChart(chartTitle, xTitle, yTitle, xyDataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setTextAntiAlias(false);
		//设置背景颜色
		chart.setBackgroundPaint(Color.WHITE);
		//配置字体  
        Font xfont = new Font("宋体",Font.TRUETYPE_FONT,8) ;// X轴字体
        Font yfont = new Font("SansSerif",Font.TRUETYPE_FONT,12) ;// Y轴字体
        Font legendFont = new Font("宋体",Font.PLAIN,12) ;//图例标题字体
        Font titleFont = new Font("隶书", Font.BOLD , 25) ; // 图片标题  
        
		//设置图标题title的字体
		chart.getTitle().setFont(titleFont);
		
		//设置图例的字体
		chart.getLegend().setItemFont(legendFont);
		//RectangleEdge rectangleEdge = new RectangleEdge("adf");
		
		//chart.getLegend().setPosition(position);
		
		
		//图形的绘制结构对象  
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();

		categoryplot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		categoryplot.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);

		// x轴 分类轴网格是否可见
		categoryplot.setDomainGridlinesVisible(false);//(true);
		// y轴  数据轴网格是否可见
		categoryplot.setRangeGridlinesVisible(true);
		// 虚线色彩
		categoryplot.setRangeGridlinePaint(Color.DARK_GRAY);//WHITE);
		// 虚线色彩
		categoryplot.setDomainGridlinePaint(Color.WHITE);
		//折线图的背景颜色
		categoryplot.setBackgroundPaint(Color.WHITE);//lightGray);
		// 设置轴和面板之间的距离
		categoryplot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 0D));

		/********************** 横轴 x *****************/
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setLabelFont(xfont);// 轴标题
		domainAxis.setTickLabelFont(xfont);// 轴数值
		//domainAxis.setLabelPaint(Color.BLUE);//轴标题的颜色
		//domainAxis.setTickLabelPaint(Color.BLUE);//轴数值的颜色

		//横轴 lable 的位置   横轴上的 Lable 45度倾斜 DOWN_45
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.0);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.0);

		/********************** 纵轴 y *****************/
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();//ValueAxis
		numberaxis.setLabelFont(yfont);
		numberaxis.setTickLabelFont(yfont);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);
		numberaxis.setInverted(false);

		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
		lineandshaperenderer.setBaseShapesVisible(false);//(true); // series 点（即数据点）可见
		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见

		// 显示折点数据
//		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		lineandshaperenderer.setBaseItemLabelsVisible(true);
		
		return chart;
	}
	public static void main(String args[]){
		double [][] data = new double[][]{{533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724,533,214,614,542,724},
				{462,836,345,854,224,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456,245,614,751,332,456},
				{245,614,751,332,456,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224,462,836,345,854,224}};
		String[] rowKeys = {"宝马","奔驰","大众"};
		String[] columnKeys = {"北京", "上海", "广州", "成都", "深圳",
				"北京1", "上海1", "广州1", "成都1", "深圳1",
				"北京2", "上海2", "广州2", "成都2", "深圳2",
				"北京3", "上海3", "广州3", "成都3", "深圳3",
				"北京4", "上海4", "广州4", "成都4", "深圳4",
				"北京5", "上海5", "广州5", "成都5", "深圳5",
				"北京6", "上海6", "广州6", "成都6", "深圳6",
				"北京7", "上海7", "广州7", "成都7", "深圳7",
				"北京8", "上海8", "广州8", "成都8", "深圳8"};
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		try {
			FileOutputStream fos = new FileOutputStream("c:/uploadFiles/abc.png");
			ChartUtilities.writeChartAsPNG(fos, new CreateJFreeChart().makeLineAndShapeChart("各地汽车销量", "城市", "销量", dataset), 500, 380);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
