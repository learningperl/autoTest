package com.test.tools;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

import com.test.statics.property;

public class ImageTool {	//图形处理类
	public static int width=0;
	public static int height=0;
	public static String path;
	
	public ImageTool() {  
        width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //要截取的宽度
		height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  //要截取的高度  
		path = property.readRcErpURL("imgPath")+ "/";
    }
	
	//全屏截图
	public static String ScreenSnapshot(String caseid){
		String filename = Long.toString(System.currentTimeMillis());
		filename = "caseid=" + caseid + "-" + filename + ".jpg";
		
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(new Rectangle(width,height));  //全屏截取
	        //image = image.getSubimage(0, 0, 200, 500);
			//保存图片
	        ImageIO.write (image, "png" , new File(path + filename));
		} catch (Exception e) {
			System.out.println("截图失败！");
			e.printStackTrace();
		}  
		robot = null;
		return filename;
	}
	
	public static void ScreenSnapshot(int x,int y,int width,int height,String filePath){

		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(new Rectangle(x,y,width,height));  //自定义截取
	        //image = image.getSubimage(0, 0, 200, 500);
			//保存图片
	        ImageIO.write (image, "png" , new File(filePath));
		} catch (Exception e) {
			System.out.println("截图失败！");
			e.printStackTrace();
		}
		robot = null;
	}
	
	/**
	 * 将图片写入到磁盘
	 * @param img 图片数据流
	 * @param fileName 文件保存时的名称
	 */
	public static void writeImageToDisk(byte[] img, String filePath){
		try {
			File file = new File(filePath);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			System.out.println("图片已经写入到"+filePath);
		} catch (Exception e) {
			System.out.println("log::error:图片写入时出错。");
			e.printStackTrace();
		}
	}
	
	public static void DelImageFromDisk(String filePath){
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			
		} catch (Exception e) {
			System.out.println("log::error:图片删除时出错。");
			e.printStackTrace();
		}
	}
}
