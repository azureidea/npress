package nc.liat6.npress.service;

import java.util.List;
import nc.liat6.npress.bean.Link;

/**
 * 友情链接业务接口
 * 
 * @author 6tail
 * 
 */
public interface ILinkService{

  /**
   * 获取友情链接列表
   * 
   * @return 链接列表
   */
  public List<Link> listLinks();

  /**
   * 根据ID获取友情链接
   * 
   * @param id 友情链接ID
   * @return 链接
   */
  public Link getLink(long id);
}