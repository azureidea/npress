package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.dao.DaoAdapter;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
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

  @Override
  public List<Link> listLinks(){
    List<Link> links = Dao.list(Link.class,new DaoAdapter(){
      @Override
      public List<Bean> list(ITrans t){
        return t.getSelecter().table("T_LINK").asc("C_INDEX").select();
      }
      @Override
      public IBeanRule rule(){
        return linkAdapter;
      }
    });
    return links;
  }

  @Override
  public Link getLink(final long id){
    Link m = Dao.one(Link.class,new DaoAdapter(){
      @Override
      public Bean one(ITrans t){
        return t.getSelecter().table("T_LINK").where("C_ID",id).one();
      }
      @Override
      public IBeanRule rule(){
        return linkAdapter;
      }
    });
    return m;
  }
}
