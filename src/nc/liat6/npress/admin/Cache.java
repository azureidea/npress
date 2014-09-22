package nc.liat6.npress.admin;

import java.io.File;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.Global;

/**
 * 缓存管理
 * @author 6tail
 *
 */
public class Cache{

  /**
   * 缓存管理页面
   * @return
   */
  public Object page(){
    Page p = new Page("/admin/cache/page.jsp");
    File dir = new File(WebContext.REAL_PATH,Global.CONFIG_SERVICE.getConfig("CACHE_DIR").getValue());
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    int fileCount = dir.listFiles().length;
    p.set("dir",dir.getAbsolutePath());
    p.set("fileCount",fileCount);
    return p;
  }

  /**
   * 缓存管理页面
   * @return
   */
  public Object clear(){
    File dir = new File(WebContext.REAL_PATH,Global.CONFIG_SERVICE.getConfig("CACHE_DIR").getValue());
    if(!dir.exists()||!dir.isDirectory()){
      dir.mkdirs();
    }
    File[] fs = dir.listFiles();
    for(File f:fs){
      f.delete();
    }
    return new Tip("缓存文件已清空");
  }
}
