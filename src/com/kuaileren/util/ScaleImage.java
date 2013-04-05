package com.kuaileren.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ScaleImage {
	public static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) {

		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();

		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	public static void saveImageAsJpg(String saveToFileStr, int width, int hight)
			throws Exception {

		BufferedImage srcImage;
		String imgType = "JPEG";
		if (saveToFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}
		if (saveToFileStr.toLowerCase().endsWith(".gif")) {
			imgType = "GIF";
		}
		
		File saveFile = new File(saveToFileStr);
		File fromFile = new File(saveToFileStr);
		srcImage = ImageIO.read(fromFile);
		
		if(srcImage.getWidth()<width && srcImage.getHeight()<hight){
			return ;
		}
		
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveFile);

	}

	public static String makeScaleImage(String httpUrl, String targetDirectory)throws Exception {
		String fileName = new Date().getTime() + ".jpg";
		String savePath = targetDirectory + fileName;
		
		String returnPicPath = fileName+"?path="+httpUrl;
		
		try {
			saveImageToDisk(httpUrl, savePath);
			saveImageAsJpg(savePath, 300, 300);
		} catch (Exception e) {
			System.out.println(e);
		}

		return returnPicPath;
	}


	  /**
     * 给图片添加水印
     * 
     * @param filePath
     *            需要添加水印的图片的路径
     * @param markContent
     *            水印的文字
     * @param markContentColor
     *            水印文字的颜色
     * @param qualNum
     *            图片质量
     * @return
     */
    public static boolean  createMark(String filePath, String markContent,
            Color markContentColor, float qualNum) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image theImg = imgIcon.getImage();
        int width = theImg.getWidth(null);
        int height = theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.setColor(markContentColor);
        g.setBackground(Color.white);
        g.drawImage(theImg, 0, 0, null);
        g.drawString(markContent, width-120, height-20); // 添加水印的文字和设置水印文字出现的内容
  
        Font f1 = new Font("TimesRoman",Font.BOLD,24);
        g.setFont(f1);
    
        
        g.dispose();
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(qualNum, true);
            encoder.encode(bimage, param);
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
    /*
     * public final static String getPressImgPath() { return ApplicationContext
     * .getRealPath("/template/data/util/shuiyin.gif"); }
     */

    /**
     * 把图片印刷到图片上
     * 
     * @param pressImg --
     *            水印文件
     * @param targetImg --
     *            目标文件
     * @param x
     *            --x坐标
     * @param y
     *            --y坐标
     */
    public final static void pressImage(String pressImg, String targetImg) {
        try {
            //目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            //水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao)-20,
                    (height - height_biao)-20, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	private static void saveImageToDisk(String httpUrl, String savePath) {
		URL url = null;
		BufferedInputStream in = null;
		FileOutputStream file = null;

		try {
			url = new URL(httpUrl);
			in = new BufferedInputStream(url.openStream());
			file = new FileOutputStream(new File(savePath));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	public static void main(String argv[]) {
		try {

			String httpUrl = "http://ww1.sinaimg.cn/bmiddle/61e64a12tw1dl3pu5pf5qj.jpg";

			
			String imagePic = makeScaleImage(httpUrl,"E:/");
			
			System.out.println(imagePic);

			httpUrl = httpUrl.replaceFirst(httpUrl, imagePic);
			
			System.out.println(httpUrl);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
