package nc.liat6.npress.cache;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.web.response.Page;

/**
 * 缓存文件控制器
 * 
 * @author 6tail
 * 
 */
public class CacheAction{

  /**
   * 返回缓存文件页面
   * 
   * @return
   */
  public Object getFile(){
    String file = Context.get("file");
    return new Page("/cache/"+file);
  }
}
