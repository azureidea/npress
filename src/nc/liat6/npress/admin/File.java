package nc.liat6.npress.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.exception.BadUploadException;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.execute.upload.UploadedFile;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.util.ImageHelper;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.upload.FileUploader;
import nc.liat6.npress.Global;

/**
 * 文件上传
 * @author 6tail
 *
 */
public class File{
 
  public Object upload(){
    Request r = Context.get(Statics.REQUEST);
    FileUploader uploader = r.find(WebExecute.TAG_UPLOADER);
    UploadedFile file = uploader.getFile();
    String dirString = Global.CONFIG_SERVICE.getConfig("UPLOAD_DIR").getValue();
    java.io.File dir = new java.io.File(WebContext.REAL_PATH,dirString);
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    String fileName = ID.next()+"."+file.getSuffix();
    try{
      file.writeTo(new java.io.File(dir,fileName));
    }catch(IOException e){
      throw new BadUploadException("文件上传失败",e);
    }
    return new Json(dirString+"/"+fileName);
  }
  
  public Object uploadPic(){
    Request r = Context.get(Statics.REQUEST);
    FileUploader uploader = r.find(WebExecute.TAG_UPLOADER);
    UploadedFile file = uploader.getFile("jpg","gif","bmp","png");
    String dirString = Global.CONFIG_SERVICE.getConfig("UPLOAD_DIR").getValue();
    int w = Integer.parseInt(Global.CONFIG_SERVICE.getConfig("PIC_WIDTH").getValue());
    int h = Integer.parseInt(Global.CONFIG_SERVICE.getConfig("PIC_HEIGHT").getValue());
    java.io.File dir = new java.io.File(WebContext.REAL_PATH,dirString);
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    String fileName = ID.next()+".jpg";
    try{
      BufferedImage img = ImageHelper.image(file.getBody());
      img = ImageHelper.resize(img,w,h);
      ImageHelper.writeJPG(img,new java.io.File(dir,fileName));
    }catch(IOException e){
      throw new BadUploadException("文件上传失败",e);
    }
    return new Json(dirString+"/"+fileName);
  }
  
  public Object uploadBigPic(){
    Request r = Context.get(Statics.REQUEST);
    FileUploader uploader = r.find(WebExecute.TAG_UPLOADER);
    UploadedFile file = uploader.getFile("jpg","gif","bmp","png");
    String dirString = Global.CONFIG_SERVICE.getConfig("UPLOAD_DIR").getValue();
    java.io.File dir = new java.io.File(WebContext.REAL_PATH,dirString);
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    String fileName = ID.next()+".jpg";
    try{
      BufferedImage img = ImageHelper.image(file.getBody());
      ImageHelper.writeJPG(img,new java.io.File(dir,fileName));
    }catch(IOException e){
      throw new BadUploadException("文件上传失败",e);
    }
    return new Json(WebContext.CONTEXT_PATH+"/"+dirString+"/"+fileName);
  }
}
