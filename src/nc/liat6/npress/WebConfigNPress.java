package nc.liat6.npress;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nc.liat6.frame.Factory;
import nc.liat6.frame.execute.IExecute;
import nc.liat6.frame.web.config.IWebManager;
import nc.liat6.frame.web.config.WebConfig;
import nc.liat6.frame.web.upload.UploadStatus;
import nc.liat6.npress.bean.Config;
import nc.liat6.npress.init.IInit;

/**
 * 网站配置
 *
 * @author 6tail
 *
 */
public class WebConfigNPress extends WebConfig{

  @Override
  public void init(){
    super.init();
    // 初始化
    List<String> inits = Factory.getImpls(IInit.class.getName());
    for(String klass:inits){
      IInit initer = Factory.getCaller().newInstance(klass);
      initer.init();
    }
  }

  @Override
  public String getErrorPage(){
    return "/themes/"+getGlobalVars().get("theme")+"/error.jsp";
  }

  @Override
  public Map<String,Object> getGlobalVars(){
    Map<String,Object> m = super.getGlobalVars();
    //读取配置
    List<Config> l = Global.CONFIG_SERVICE.listConfigs();
    for(Config config:l){
      m.put(config.getKey(),config.getValue());
    }
    return m;
  }

  @Override
  public List<String> getForbiddenPaths(){
    List<String> l = super.getForbiddenPaths();
    String pkg = this.getClass().getPackage().getName();
    // 屏蔽访问
    l.add("/"+pkg);
    l.add("/"+pkg.replace(".","-"));
    return l;
  }

  public List<String> getAllowPaths(){
    List<String> l = new ArrayList<String>();
    l.add("/"+UploadStatus.class.getName().replace(".","-"));
    return l;
  }

  @Override
  public IWebManager getWebManager(){
    return new WebManagerNPress(this);
  }

  @Override
  public IExecute getExecuter(){
    return new ExecuterNPress();
  }

  @Override
  public void start(){
    super.start();
  }
}
