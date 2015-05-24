package nc.liat6.npress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.exception.BadUploadException;
import nc.liat6.frame.execute.Client;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.execute.Response;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.config.IWebConfig;
import nc.liat6.frame.web.config.WebManager;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.frame.web.response.mobile.Toast;
import nc.liat6.frame.web.upload.FileUploader;
import nc.liat6.npress.cache.CacheAction;
import nc.liat6.npress.exception.UserLoginException;

/**
 * WEB管理器
 * 
 * @author 6tail
 * 
 */
public class WebManagerNPress extends WebManager{
  /** 移动端浏览器标识 */
  static final String[] MOBILE_AGENT = {"iphone","ipad","android","phone","mobile","wap","netfront","java","opera mobi","opera mini","ucweb","windows ce","symbian","series","webos","sony","blackberry","dopod","nokia","samsung","palmsource","xda","pieplus","meizu","midp","cldc","motorola","foma","docomo","up.browser","up.link","blazer","helio","hosin","huawei","novarra","coolpad","webos","techfaith","palmsource","alcatel","amoi","ktouch","nexian","ericsson","philips","sagem","wellcom","bunjalloo","maui","smartphone","iemobile","spice","bird","zte-","longcos","pantech","gionee","portalmmm","jig browser","hiptop","benq","haier","^lct","320x320","240x320","176x220","w3c ","acs-","alav","alca","amoi","audi","avan","benq","bird","blac","blaz","brew","cell","cldc","cmd-","dang","doco","eric","hipt","inno","ipaq","java","jigs","kddi","keji","leno","lg-c","lg-d","lg-g","lge-","maui","maxo","midp","mits","mmef","mobi","mot-","moto","mwbp","nec-","newt","noki","oper","palm","pana","pant","phil","play","port","prox","qwap","sage","sams","sany","sch-","sec-","send","seri","sgh-","shar","sie-","siem","smal","smar","sony","sph-","symb","t-mo","teli","tim-","tsm-","upg1","upsi","vk-v","voda","wap-","wapa","wapi","wapp","wapr","webc","winw","winw","xda","xda-","Googlebot-Mobile"};
  /** 需要做缓存的方法列表 */
  public static final List<String> cacheMethods = new ArrayList<String>();
  static{
    cacheMethods.add("page");
    cacheMethods.add("detail");
  }

  /**
   * 判断是否移动端浏览器
   * 
   * @param req
   * @return
   */
  private boolean isMobile(HttpServletRequest req){
    String userAgent = req.getHeader("User-Agent");
    if(null!=userAgent){
      for(String ma:MOBILE_AGENT){
        if(userAgent.toLowerCase().contains(ma)){
          return true;
        }
      }
    }
    return false;
  }

  public WebManagerNPress(IWebConfig config){
    super(config);
  }
  
  @Override
  public Object failed(Throwable e){
    Throwable cause = e;
    while(null!=cause&&null!=cause.getCause()){
      cause = cause.getCause();
    }
    if(!(cause instanceof BadException)&&!(cause instanceof UserLoginException)){
      Logger.getLog().error(null==e?null:e.getMessage(),e);
    }
    String r = null==cause?null:cause.getMessage();
    Request req = Context.get(Statics.REQUEST);
    Client client = req.getClient();
    HttpServletRequest oreq = req.find(Statics.FIND_REQUEST);
    Response res = Context.get(Statics.RESPONSE);
    HttpServletResponse ores = res.find(Statics.FIND_RESPONSE);
    String headAjax = oreq.getHeader("x-requested-with");
    //非ajax请求
    if(null==headAjax||!"XMLHttpRequest".equals(headAjax)){
      //如果需要pc端登录的，跳转到pc登录
      if(cause instanceof UserLoginException){
        return new Page("/go_login.jsp");
      }
      // 文件上传异常，转换为JSON返回
      if(cause instanceof BadUploadException||req.get(FileUploader.ARG_ID).length()>0){
        Json json = new Json(r);
        json.setMsg(r);
        json.setSuccess(false);
        return json;
      }
      // 可被检测到的错误处理
      if(cause instanceof ClassNotFoundException||cause instanceof NoSuchMethodException){
        String errorPage = config.getErrorPage(req,404);
        if(null==errorPage){
          ores.setStatus(404);
          return null;
        }else{
          Page p = new Page(errorPage);
          p.set("e",cause);
          p.setStatus(404);
          return p;
        }
      }else{
        String errorPage = config.getErrorPage(req,500);
        if(null==errorPage){
          ores.setStatus(500);
          return null;
        }else{
          Page p = new Page(errorPage);
          p.set("e",cause);
          p.setStatus(500);
          return p;
        }
      }
    }else{
      // ajax请求的错误处理
      if(client.isMobile()){
        Toast toast = new Toast();
        toast.setMsg(r);
        if(cause instanceof BadException){
          BadException be = (BadException)cause;
          toast.setData(be.getData());
        }
        toast.setSuccess(false);
        return toast;
      }else{
        Tip tip = new Tip();
        tip.setMsg(r);
        if(cause instanceof BadException){
          BadException be = (BadException)cause;
          tip.setData(be.getData());
        }
        tip.setSuccess(false);
        return tip;
      }
    }
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
      if(cacheMethods.contains(method)&&"true".equalsIgnoreCase(Global.CONFIG_SERVICE.getConfig("CACHE_ENABLE").getValue())){
        // 传来的id参数
        String id = r.getParameter("id");
        id = null==id?"":id;
        // 传来的页码
        int pageNum = 1;
        try{
          pageNum = Integer.parseInt(r.getParameter(Request.PAGE_NUMBER_VAR));
        }catch(Exception e){}
        // 缓存文件唯一名称
        String fileName = (isMobile(r)?"mobile":"pc")+"-"+klass+"-"+id+"-"+pageNum+".html";
        File dir = new File(WebContext.REAL_PATH,Global.CONFIG_SERVICE.getConfig("CACHE_DIR").getValue());
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
        Client client = r.getClient();
        String theme = Global.CONFIG_SERVICE.getConfig("theme").getValue();
        //如果是移动客户端，转到mobile目录
        p.setUri("/themes/"+theme+"/"+(client.isMobile()?"mobile":"pc")+"/"+p.getUri());
      }
    }
  }
}