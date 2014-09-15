package nc.liat6.npress;

import nc.liat6.frame.Factory;
import nc.liat6.npress.service.IConfigService;

/**
 * 全局常量，不要更改
 * 
 * @author 6tail
 * 
 */
public class Global{

  /** 用户session标识 */
  public static final String SESSION_USER = "user";
  
  public static IConfigService CONFIG_SERVICE = Factory.getCaller().newInstance(IConfigService.class);
}