package nc.liat6.npress.bean.adapter;

import java.util.HashMap;
import java.util.Map;
import nc.liat6.frame.db.entity.IBeanRule;

/**
 * Config适配器
 * @author 6tail
 *
 */
public class ConfigAdapter implements IBeanRule{

  private static final Map<String,String> map = new HashMap<String,String>();
  static{
    map.put("name","C_NAME");
    map.put("key","C_KEY");
    map.put("value","C_VALUE");
    map.put("description","C_DESC");
  }

  @Override
  public String getKey(String property){
    return map.get(property);
  }
}
