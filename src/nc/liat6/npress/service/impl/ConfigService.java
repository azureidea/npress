package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.execute.Request;
import nc.liat6.npress.bean.Config;
import nc.liat6.npress.bean.adapter.ConfigAdapter;
import nc.liat6.npress.service.IConfigService;

/**
 * 配置业务实现
 *
 * @author 6tail
 *
 */
public class ConfigService implements IConfigService{
  private static final Map<String,Config> configs = new HashMap<String,Config>();
  /** Config转换适配器 */
  private static final IBeanRule configAdapter = new ConfigAdapter();

  public List<Config> listConfigs(){
    if(configs.size()<1){
      fresh();
    }
    List<Config> l = new ArrayList<Config>();
    for(String key:configs.keySet()){
      l.add(configs.get(key));
    }
    return l;
  }

  public Config getConfig(String key){
    if(configs.size()<1){
      fresh();
    }
    return configs.get(key);
  }

  public void fresh(){
    configs.clear();
    List<Config> l = Dao.getSelecter().table("T_CONFIG").select(Config.class,configAdapter);
    for(Config m:l){
      configs.put(m.getKey(),m);
    }
  }

  public void updateToApplication(){
    Request r = Context.get(Statics.REQUEST);
    HttpSession session = r.find(Statics.FIND_SESSION);
    ServletContext sc = session.getServletContext();
    for(String key:configs.keySet()){
      sc.setAttribute(key,configs.get(key).getValue());
    }
  }
}