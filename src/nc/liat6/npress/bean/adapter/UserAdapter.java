package nc.liat6.npress.bean.adapter;

import java.util.HashMap;
import java.util.Map;
import nc.liat6.frame.db.entity.IBeanRule;

/**
 * User适配器
 * @author 6tail
 *
 */
public class UserAdapter implements IBeanRule{

  private static final Map<String,String> map = new HashMap<String,String>();
  static{
    map.put("id","C_ID");
    map.put("account","C_ACCOUNT");
    map.put("name","C_NAME");
    map.put("password","C_PASSWORD");
    map.put("admin","C_IS_ADMIN");
  }

  @Override
  public String getKey(String property){
    return map.get(property);
  }
}
