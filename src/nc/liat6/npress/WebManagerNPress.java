package nc.liat6.npress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.config.IWebConfig;
import nc.liat6.frame.web.config.WebManager;
import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.cache.CacheAction;

/**
 * WEB管理器
 * 
 * @author 6tail
 * 
 */
public class WebManagerNPress extends WebManager{

  /** 网站使用的主题 */
  private String theme;
  
  /** 需要做缓存的方法列表 */
  public static final List<String> cacheMethods = new ArrayList<String>();
  static{
    cacheMethods.add("page");
    cacheMethods.add("detail");
  }

  public WebManagerNPress(IWebConfig config){
    super(config);
    theme = config.getGlobalVars().get("theme")+"";
  }

  @Override
  public ClassMethod before(String path){
    // 匹配路径，package-package-Class/method
    if(!path.matches("[/].{1,}[-].{1,}[/]\\w{1,}")){
      return null;
    }
    String[] keys = path.split("/");
    // package-package.Class
    String klass = keys[1];
    // method
    String method = keys[2];
    // package-package-Class转为package.package.Class
    klass = klass.replace("-",".");
    ClassMethod cm = new ClassMethod();
    cm.setKlass(klass);
    cm.setMethod(method);
    Context.set("NPRESS_CLASS_METHOD",cm);
    ClassMethod ncm = new ClassMethod();
    ncm.setKlass(klass);
    ncm.setMethod(method);
    // 如果请求以action.开头，则自动重定向到nc.liat6.npress.action包
    if(klass.startsWith("action.")){
      ncm.setKlass("nc.liat6.npress."+klass);
      HttpServletRequest r = Context.get("NLF_HTTP_SERVLET_REQUEST");
      // 需要请求缓存
      if(cacheMethods.contains(method)){
        // 传来的id参数
        String id = r.getParameter("id");
        id = null==id?"":id;
        // 传来的页码
        int pageNum = 1;
        try{
          pageNum = Integer.parseInt(r.getParameter(Request.PAGE_NUMBER_VAR));
        }catch(Exception e){}
       
        // 缓存文件唯一名称
        String fileName = klass+"-"+id+"-"+pageNum+".html";
        File dir = new File(WebContext.REAL_PATH,Global.DEFAULT_CACHE_DIR);
        if(!dir.exists()||!dir.isDirectory()){
          dir.mkdirs();
        }
        File file = new File(dir,fileName);
        // 如果缓存文件存在，就转到CacheAction显示缓存文件
        if(file.exists()){
          ncm.setKlass(CacheAction.class.getName());
          ncm.setMethod("getFile");
          Context.set("file",fileName);
          Context.set("NPRESS_CLASS_METHOD",ncm);
        }
      }
    }else if(klass.startsWith("admin.")){
      // 如果请求以admin.开头，则自动重定向到nc.liat6.npress.admin包
      ncm.setKlass("nc.liat6.npress."+klass);
    }
    return ncm;
  }

  @Override
  public void filter(){
    super.filter();
    Request r = Context.get(Statics.REQUEST);
    //返回结果
    Object o = Context.get(WebExecute.EXECUTE_RETURN);
    // 如果返回的是Page，自动重定向到当前主题下
    if(o instanceof Page){
      Page p = (Page)o;
      if(!p.getUri().startsWith("/")){
        //如果是移动客户端，转到mobile目录
        if(Request.CLIENT_TYPE_MOBILE==r.getClientType()){
          p.setUri("/themes/"+theme+"/mobile/"+p.getUri());
        }else{
          p.setUri("/themes/"+theme+"/pc/"+p.getUri());
        }
      }
    }
  }
}
