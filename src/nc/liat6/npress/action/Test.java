package nc.liat6.npress.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.execute.upload.IUploader;
import nc.liat6.frame.execute.upload.UploadedFile;
import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.util.ImageHelper;
import nc.liat6.frame.util.UUID;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.response.Paging;

/**
 * 测试类
 *
 * @author 6tail
 *
 */
public class Test{
  public Object uploadPic() throws IOException{
    Request r = Context.get(Statics.REQUEST);
    IUploader uploader = r.find(Statics.FIND_UPLOADER);
    UploadedFile f = uploader.getFile("jpg","gif","bmp","png");
    String dirString = "demo_pic";
    java.io.File dir = new java.io.File(WebContext.REAL_PATH,dirString);
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    String fileName = UUID.next()+".jpg";
    BufferedImage img = ImageHelper.image(f.getBody());
    ImageHelper.writeJPG(img,new File(dir,fileName));
    return new Json(WebContext.CONTEXT_PATH+"/"+dirString+"/"+fileName);
  }
  
  public Object uploadPics() throws IOException{
    Request r = Context.get(Statics.REQUEST);
    IUploader uploader = r.find(Statics.FIND_UPLOADER);
    List<UploadedFile> fs = uploader.getFiles("jpg","gif","bmp","png");
    String dirString = "demo_pic";
    java.io.File dir = new java.io.File(WebContext.REAL_PATH,dirString);
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    List<String> files = new ArrayList<String>();
    for(UploadedFile f:fs){
      String fileName = UUID.next()+".jpg";
      files.add(WebContext.CONTEXT_PATH+"/"+dirString+"/"+fileName);
      BufferedImage img = ImageHelper.image(f.getBody());
      ImageHelper.writeJPG(img,new File(dir,fileName));
    }
    return new Json(files);
  }
  
  /**
   * 自动分页
   *
   * @return
   */
  public Object paging(){
    Request r = Context.get(Statics.REQUEST);
    ITrans t = TransFactory.getTrans();
    PageData pd = t.getSelecter().table("T_ARTICLE").desc("C_ID").page(r.getPageNumber(),5);
    t.rollback();
    Paging p = new Paging();
    p.setPageData(pd);
    p.setUri("/demo/jsp/paging.jsp");
    return p;
  }
}
