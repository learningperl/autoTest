package com.test.tools;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import com.test.statics.outputList;

public class ImageTool {	//图形处理类
	public static int width=0;
	public static int height=0;
	
	public ImageTool() {  
        width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //要截取的宽度
		height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  //要截取的高度  
    }
	
	public static String ScreenSnapshot(String caseid){
		String filename = Long.toString(System.currentTimeMillis());
		filename = "caseid=" + caseid + "-" + filename + ".jpg";
		
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(new Rectangle(width,height));  //全屏截取
	        //image = image.getSubimage(0, 0, 200, 500);
			//保存图片
	        ImageIO.write (image, "png" , new File(outputList.path + "\\workspace1\\WebTester\\WebContent\\static\\imgs\\"+filename));
		} catch (Exception e) {
			System.out.println("截图失败！");
			e.printStackTrace();
		}  
		return filename;
	}
}
