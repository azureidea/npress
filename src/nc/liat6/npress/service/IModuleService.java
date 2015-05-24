package nc.liat6.npress.service;

import java.util.List;
import nc.liat6.npress.bean.Module;

/**
 * 模块业务接口
 * 
 * @author 6tail
 * 
 */
public interface IModuleService{

  /**
   * 获取模块列表
   * 
   * @return 模块列表
   */
  List<Module> listModules();

  /**
   * 根据ID获取模块
   * 
   * @param id 模块ID
   * @return 模块
   */
  Module getModule(long id);
  
  /**
   * 删除模块
   * @param id 模块ID
   */
  void deleteModule(long id);
}