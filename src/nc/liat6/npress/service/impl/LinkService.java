package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.npress.bean.Link;
import nc.liat6.npress.bean.adapter.LinkAdapter;
import nc.liat6.npress.service.ILinkService;

/**
 * 友情链接业务实现
 *
 * @author 6tail
 *
 */
public class LinkService implements ILinkService{
  /** Link转换适配器 */
  private static final IBeanRule linkAdapter = new LinkAdapter();

  public List<Link> listLinks(){
    return Dao.getSelecter().table("T_LINK").asc("C_INDEX").select(Link.class,linkAdapter);
  }

  public Link getLink(final long id){
    return Dao.getSelecter().table("T_LINK").where("C_ID",id).one(Link.class,linkAdapter);
  }
}