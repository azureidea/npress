package nc.liat6.npress;

import java.util.List;
import java.util.Map;
import nc.liat6.frame.Factory;
import nc.liat6.frame.execute.IExecute;
import nc.liat6.frame.web.config.IWebManager;
import nc.liat6.frame.web.config.WebConfig;
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
  }

  @Override
  public String getErrorPage(){
    return "/themes/"+getGlobalVars().get("theme")+"/error.jsp";
  }

  @Override
  public Map<String,Object> getGlobalVars(){
    Map<String,Object> m = super.getGlobalVars();
    // 设置网站作者
    m.put("WEB_AUTHOR",Global.DEFAULT_WEB_AUTHOR);
    // 设置网站名
    m.put("WEB_NAME",Global.DEFAULT_WEB_NAME);
    // 设置网站关键词
    m.put("WEB_KEY",Global.DEFAULT_WEB_KEY);
    // 设置网站描述
    m.put("WEB_DESC",Global.DEFAULT_WEB_DESCIPTION);
    // 设置网站版权
    m.put("WEB_COPYRIGHT",Global.DEFAULT_WEB_COPYRIGHT);
    // 设置网站主题
    m.put("theme",Global.DEFAULT_THEME);
    return m;
  }

  @Override
  public List<String> getForbiddenPaths(){
    List<String> l = super.getForbiddenPaths();
    // 屏蔽访问
    l.add("/nc.liat6.npress");
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
    // 初始化
    List<String> inits = Factory.getImpls(IInit.class.getName());
    for(String klass:inits){
      IInit initer = Factory.getCaller().newInstance(klass);
      initer.init();
    }
  }
}
