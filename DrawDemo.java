package com.practice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 画一个图，坐标系中x，y分别为范围在[-i,i]内的整数，(|x|=i,0)与(0 ,|y|=1)
 * @author User
 *
 */
public class DrawDemo {

	public static void main(String[] args) {

		//图片宽度
		int weigh = 800;
		//图片高度
		int hight = 800;
		//坐标轴与图片边界距离
		int margin =10;
		//线条密度（轴线交点间距）
		int step = 10;
		//图片保存文件夹
		String fileName1 = "E:/mypics1/";
		String fileName2 = "E:/mypics2/";
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		if (!file2.exists()) {
			file2.mkdirs();
		}
		//更改step的值，生成多张图片
		for (int i = 1; i <= 100; i++) {
			step = i;
			String name = "000".concat(String.valueOf(step));
			String fullName = fileName1 + name.substring(name.length() - 3) + ".jpg";
			//图片生成方法1
			myDrawFunction1(weigh, hight, margin, step, fullName);
			//图片生成方法2，更改保存文件夹
			fullName = fileName2 + name.substring(name.length() - 3) + ".jpg";
			myDrawFunction2(weigh, hight, margin, step, fullName);
			

		}
		
		
		
		

	}

	/**
	 * 图片生成方法2，用两个循环，定一个点，另一个点循环匹配。
	 * @param weigh 图片宽
	 * @param hight 图片高
	 * @param margin 图片边距
	 * @param step 图片密度
	 * @param fileName 文件保存路径+名称
	 */
	private static void myDrawFunction2(int weigh, int hight, int margin, int step, String fileName) {
		BufferedImage bufferedImage = new BufferedImage(weigh, hight, BufferedImage.TYPE_INT_RGB);
		bufferedImage = drawBackground(bufferedImage, weigh, hight, margin);

		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setColor(Color.RED);

		int centerX = (int) Math.floor(weigh / 2);
		int centerY = (int) Math.floor(hight / 2);
		int count = Math.floorDiv(weigh < hight ? weigh-centerX-margin : hight-centerY-margin, step);
		
		for(int i = 0-count;i<=count;i++) {
			for(int j =0-count;j<=count;j++) {
				if((Math.abs(i)+Math.abs(j))==(count+1)) {
					graphics.drawLine(centerX+i*step, centerY, centerX,centerY+j*step);
				}
			}
		}
		

		graphics.setColor(Color.YELLOW);
		int size = (int) Math.floor(hight / 50);
		Font font = new Font("Arial", Font.PLAIN, size);
		graphics.setFont(font);
		graphics.setBackground(Color.BLACK);
		//将图片的一些信息写在图片左上角
		graphics.drawString("weigh=" + weigh, size, size);
		graphics.drawString("hight=" + hight, size, 2 * size + 1);
		graphics.drawString("margin=" + margin, size, 3 * size + 2);
		graphics.drawString("step=" + step, size, 4 * size + 3);

		try {
			ImageIO.write(bufferedImage, "JPEG", new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 图片生成方法1，人为判断取点，定一个点，然后算出另一个点的坐标。
	 * @param weigh 图片宽
	 * @param hight 图片高
	 * @param margin 图片边距
	 * @param step 图片密度
	 * @param fileName 文件保存路径+名称
	 */
	private static void myDrawFunction1(int weigh, int hight, int margin, int step, String fileName) {
		BufferedImage bufferedImage = new BufferedImage(weigh, hight, BufferedImage.TYPE_INT_RGB);
		bufferedImage = drawBackground(bufferedImage, weigh, hight, margin);
//		String desFile = fileName;

		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setColor(Color.RED);

		int centerX = (int) Math.floor(weigh / 2);
		int centerY = (int) Math.floor(hight / 2);
//		int i = (int)Math.floor(Math.floor( (weigh < hight ? weigh-20 : hight-20)  / step)/2);
//		int i = (int)Math.floor( (weigh < hight ? weigh-centerX-10 : hight-centerY-10)  / step);
		int count = Math.floorDiv(weigh < hight ? weigh-centerX-margin : hight-centerY-margin, step);

		for (int n = 0; n <= 2 * count; n++) {
			if (n != count ) {
				graphics.drawLine(centerX + (n - count) * step, centerY, centerX,
						centerY - (count - Math.abs(count - n) + 1) * step);
				graphics.drawLine(centerX + (n - count) * step, centerY, centerX,
						centerY + (count - Math.abs(count - n) + 1) * step);
			}
		}

		graphics.setColor(Color.YELLOW);
		int size = (int) Math.floor(hight / 50);
		Font font = new Font("Arial", Font.PLAIN, size);
		graphics.setFont(font);
		graphics.setBackground(Color.BLACK);
		//将图片的一些信息写在图片左上角
		graphics.drawString("weigh=" + weigh, size, size);
		graphics.drawString("hight=" + hight, size, 2 * size + 1);
		graphics.drawString("margin=" + margin, size, 3 * size + 2);
		graphics.drawString("step=" + step, size, 4 * size + 3);

		try {
			ImageIO.write(bufferedImage, "JPEG", new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 以图片中心为原点画个坐标系
	 * @param bufferedImage 图片实例
	 * @param weigh 图片宽
	 * @param hight 图片高
	 * @param margin 图片边距
	 * @return
	 */
	private static BufferedImage drawBackground(BufferedImage bufferedImage, int weigh, int hight ,int margin) {

		int centerX = (int) Math.floor(weigh / 2);
		int centerY = (int) Math.floor(hight / 2);

		Graphics2D graphics = bufferedImage.createGraphics();
//		graphics.setBackground(Color.WHITE);
		graphics.setColor(Color.RED);

		graphics.drawLine(margin, centerY, weigh - margin, centerY);
		graphics.drawLine(centerX, margin, centerX, hight - margin);

		graphics.drawLine(centerX - 5, margin+5, centerX, margin);
		graphics.drawLine(centerX + 5, margin+5, centerX, margin);

		graphics.drawLine(weigh - margin - 5, centerY + 5, weigh - margin, centerY);
		graphics.drawLine(weigh - margin - 5, centerY - 5, weigh - margin, centerY);

		return bufferedImage;
	}

}
