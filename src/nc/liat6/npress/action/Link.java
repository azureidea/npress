package nc.liat6.npress.action;

import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.service.ILinkService;

/**
 * 友情链接控制器
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
   * 友情链接页
   * 
   * @return
   */
  public Object page(){
    Page p = new Page("link.jsp");
    p.set("links",linkService.listLinks());
    p.deliver();
    return p;
  }
}
