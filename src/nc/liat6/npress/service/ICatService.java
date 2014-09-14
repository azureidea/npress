package nc.liat6.npress.service;

import java.util.List;
import nc.liat6.npress.bean.Cat;

/**
 * 分类业务接口
 * 
 * @author 6tail
 * 
 */
public interface ICatService{

  /**
   * 获取分类列表
   * 
   * @return 分类列表
   */
  public List<Cat> listCats();

  /**
   * 根据ID获取分类
   * 
   * @param id 分类ID
   * @return 分类
   */
  public Cat getCat(long id);
}
