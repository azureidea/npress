package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

/**
 * 模块控制器
 * 
 * @author 6tail
 * 
 */
public class Module{

  /** 模块业务接口 */
  private IModuleService moduleService;
  /** 分类业务接口 */
  private ICatService catService;

  public void setModuleService(IModuleService moduleService){
    this.moduleService = moduleService;
  }

  public void setCatService(ICatService catService){
    this.catService = catService;
  }

  /**
   * 模块内容页
   * @return
   */
  public Object detail(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    Page p = new Page("module.jsp");
    p.set("module",moduleService.getModule(id));
    p.set("modules",moduleService.listModules());
    p.set("cats",catService.listCats());
    p.deliver();
    return p;
  }
}
