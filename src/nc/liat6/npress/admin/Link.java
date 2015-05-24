package nc.liat6.npress.admin;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.response.HideJson;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.service.ILinkService;

/**
 * 后台-友情链接管理
 * 
 * @author 6tail
 * 
 */
public class Link{

  /** 友情链接业务接口 */
  private ILinkService linkService;

  public void setLinkService(ILinkService linkService){
    this.linkService = linkService;
  }

  /**
   * 添加友情链接页面
   * 
   * @return
   */
  public Object pageAdd(){
    return new Page("/admin/link/add.jsp");
  }
  
  public Object detail(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    return new HideJson(linkService.getLink(id));
  }

  /**
   * 列表页面
   * 
   * @return
   */
  public Object pageList(){
    Page p = new Page("/admin/link/list.jsp");
    p.set("links",linkService.listLinks());
    return p;
  }

  /**
   * 添加友情链接
   * 
   * @return
   */
  public Object add(){
    Request r = Context.get(Statics.REQUEST);
    String name = r.get("name");
    String url = r.get("url");
    int index = r.getInt("index");
    Validator.check(name,new RuleNotEmpty("网站名"));
    ITrans t = TransFactory.getTrans();
    String id = ID.next()+"";
    IInserter ins = t.getInserter().table("T_LINK");
    ins.set("C_ID",id);
    ins.set("C_NAME",name);
    ins.set("C_URL",url);
    ins.set("C_INDEX",index);
    ins.insert();
    t.commit();
    t.close();
    return new Tip("添加成功");
  }

  /**
   * 修改友情链接
   * 
   * @return
   */
  public Object modify(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    String name = r.get("name");
    String url = r.get("url");
    int index = r.getInt("index");
    Validator.check(name,new RuleNotEmpty("网站名"));
    ITrans t = TransFactory.getTrans();
    IUpdater iup = t.getUpdater().table("T_LINK");
    iup.where("C_ID",id);
    iup.set("C_NAME",name);
    iup.set("C_URL",url);
    iup.set("C_INDEX",index);
    iup.update();
    t.commit();
    t.close();
    return new Tip("修改成功");
  }

  /**
   * 删除友情链接
   * 
   * @return
   */
  public Object delete(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    ITrans t = TransFactory.getTrans();
    t.getDeleter().table("T_LINK").where("C_ID",id).delete();
    t.commit();
    t.close();
    return new Tip("删除成功");
  }
}
