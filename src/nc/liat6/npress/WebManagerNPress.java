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
  
  static final String[] MOBILE_AGENT = {"iphone","ipad","android","phone","mobile","wap","netfront","java","opera mobi","opera mini","ucweb","windows ce","symbian","series","webos","sony","blackberry","dopod","nokia","samsung","palmsource","xda","pieplus","meizu","midp","cldc","motorola","foma","docomo","up.browser","up.link","blazer","helio","hosin","huawei","novarra","coolpad","webos","techfaith","palmsource","alcatel","amoi","ktouch","nexian","ericsson","philips","sagem","wellcom","bunjalloo","maui","smartphone","iemobile","spice","bird","zte-","longcos","pantech","gionee","portalmmm","jig browser","hiptop","benq","haier","^lct","320x320","240x320","176x220","w3c ","acs-","alav","alca","amoi","audi","avan","benq","bird","blac","blaz","brew","cell","cldc","cmd-","dang","doco","eric","hipt","inno","ipaq","java","jigs","kddi","keji","leno","lg-c","lg-d","lg-g","lge-","maui","maxo","midp","mits","mmef","mobi","mot-","moto","mwbp","nec-","newt","noki","oper","palm","pana","pant","phil","play","port","prox","qwap","sage","sams","sany","sch-","sec-","send","seri","sgh-","shar","sie-","siem","smal","smar","sony","sph-","symb","t-mo","teli","tim-","tsm-","upg1","upsi","vk-v","voda","wap-","wapa","wapi","wapp","wapr","webc","winw","winw","xda","xda-","Googlebot-Mobile"};

  /** 网站使用的主题 */
  private String theme;
  
  /** 需要做缓存的方法列表 */
  public static final List<String> cacheMethods = new ArrayList<String>();
  static{
    cacheMethods.add("page");
    cacheMethods.add("detail");
  }
  
  private int getClientType(HttpServletRequest oreq){
    // 判断移动浏览器
    String userAgent = oreq.getHeader("User-Agent");
    if(null!=userAgent){
      for(String ma:MOBILE_AGENT){
        if(userAgent.toLowerCase().contains(ma)){
          return Request.CLIENT_TYPE_MOBILE;
        }
      }
    }
    return Request.CLIENT_TYPE_COMPUTER;
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
      if(cacheMethods.contains(method)&&"true".equalsIgnoreCase(Global.CONFIG_SERVICE.getConfig("CACHE_ENABLE").getValue())){
        // 传来的id参数
        String id = r.getParameter("id");
        id = null==id?"":id;
        // 传来的页码
        int pageNum = 1;
        try{
          pageNum = Integer.parseInt(r.getParameter(Request.PAGE_NUMBER_VAR));
        }catch(Exception e){}
        int clientType = getClientType(r);
        // 缓存文件唯一名称
        String fileName = (Request.CLIENT_TYPE_MOBILE==clientType?"mobile":"pc")+"-"+klass+"-"+id+"-"+pageNum+".html";
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
