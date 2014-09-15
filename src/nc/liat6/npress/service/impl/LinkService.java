package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
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
    ITrans t = TransFactory.getTrans();
    List<Bean> l = t.getSelecter().table("T_LINK").asc("C_INDEX").select();
    t.rollback();
    t.close();
    List<Link> links = new ArrayList<Link>(l.size());
    for(Bean o:l){
      Link m = o.toObject(Link.class,linkAdapter);
      links.add(m);
    }
    return links;
  }

  @Override
  public Link getLink(long id){
    ITrans t = TransFactory.getTrans();
    Bean o = t.getSelecter().table("T_LINK").where("C_ID",id).one();
    t.rollback();
    t.close();
    Link m = o.toObject(Link.class,linkAdapter);
    return m;
  }
}
