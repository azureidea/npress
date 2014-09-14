package nc.liat6.npress.bean.adapter;

import java.util.HashMap;
import java.util.Map;
import nc.liat6.frame.db.entity.IBeanRule;

/**
 * Article适配器
 * @author 6tail
 *
 */
public class ArticleAdapter implements IBeanRule{

  private static final Map<String,String> map = new HashMap<String,String>();
  static{
    map.put("id","C_ID");
    map.put("title","C_TITLE");
    map.put("content","C_CONTENT");
    map.put("description","C_DESC");
    map.put("day","C_DAY");
    map.put("time","C_TIME");
  }

  @Override
  public String getKey(String property){
    return map.get(property);
  }
}
