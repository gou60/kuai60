package com.kuaileren.web;

import java.awt.Color;
import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.kuaileren.util.ScaleImage;

public class UploadAction extends BaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4064307724632656476L;

	private File upload;// 实际上传文件

    private String uploadContentType; // 文件的内容类型

    private String uploadFileName; // 上传文件名

    private String fileCaption;// 上传文件时的备注
    private String image_url;
    private int resultCode;
    
    public String upload() throws Exception {
    	
        try {
        	
            String targetDirectory = context.getRealPath("/")+"upload/";
            String targetFileName = new Date().getTime()+"."+type();

            File target = new File(targetDirectory, targetFileName);
            FileUtils.copyFile(upload, target);            
            
            setUploadFileName(target.getPath());//保存文件的存放路径
            
            ScaleImage.pressImage(context.getRealPath("/")+"images/h20_kuai60.gif", target.getPath());
            
            setImage_url(request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/upload/"+target.getName());
            resultCode=0;
            
        } catch (Exception e) {
        	e.printStackTrace();
        	resultCode=1;
        }

        return SUCCESS;

    }

    
    public String type(){
    	String[] type = uploadFileName.split("\\.");
    	return type[1];
    }
    
    public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getFileCaption() {
        return fileCaption;
    }
    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
