package nc.liat6.npress.bean.adapter;

import java.util.HashMap;
import java.util.Map;
import nc.liat6.frame.db.entity.IBeanRule;

/**
 * Module适配器
 * @author 6tail
 *
 */
public class ModuleAdapter implements IBeanRule{

  private static final Map<String,String> map = new HashMap<String,String>();
  static{
    map.put("id","C_ID");
    map.put("name","C_NAME");
    map.put("type","C_TYPE");
    map.put("url","C_URL");
    map.put("content","C_CONTENT");
    map.put("home","C_IS_HOME");
    map.put("index","C_INDEX");
    map.put("pos","C_POS");
  }

  @Override
  public String getKey(String property){
    return map.get(property);
  }
}
