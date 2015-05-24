package nc.liat6.npress.service;

import java.util.List;
import nc.liat6.npress.bean.Config;

/**
 * 配置业务接口
 * 
 * @author 6tail
 * 
 */
public interface IConfigService{

  /**
   * 获取配置列表
   * 
   * @return 配置列表
   */
  public List<Config> listConfigs();

  /**
   * 根据键获取配置
   * 
   * @param key 键
   * @return 配置
   */
  public Config getConfig(String key);

  /**
   * 刷新配置
   */
  public void fresh();

  /** 更新到网站Application */
  public void updateToApplication();
}