package nc.liat6.npress.admin;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.response.HideJson;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.Tables;
import nc.liat6.npress.service.IModuleService;

/**
 * 后台-模块管理
 * 
 * @author 6tail
 * 
 */
public class Module{

  /** 模块业务接口 */
  private IModuleService moduleService;

  public void setModuleService(IModuleService moduleService){
    this.moduleService = moduleService;
  }

  /**
   * 添加模块页面
   * 
   * @return
   */
  public Object pageAdd(){
    return new Page("/admin/module/add.jsp");
  }

  /**
   * 模块列表页面
   * 
   * @return
   */
  public Object pageList(){
    Page p = new Page("/admin/module/list.jsp");
    p.set("modules",moduleService.listModules());
    return p;
  }

  /**
   * 添加模块
   * 
   * @return
   */
  public Object add(){
    Request r = Context.get(Statics.REQUEST);
    String name = r.get("name");
    String type = r.get("type");
    String url = r.get("url");
    String home = r.get("home");
    String content = r.get("content");
    int index = r.getInt("index");
    int pos = r.getInt("pos");
    Validator.check(name,new RuleNotEmpty("模块名"));
    IInserter ins = Dao.getInserter().table(Tables.MODULE);
    ins.set("C_ID",ID.next()+"");
    ins.set("C_NAME",name);
    ins.set("C_TYPE",type);
    ins.set("C_URL",url);
    ins.set("C_IS_HOME",home);
    ins.set("C_CONTENT",content);
    ins.set("C_INDEX",index);
    ins.set("C_POS",pos);
    ins.insert();
    return new Tip("添加成功");
  }

  /**
   * 修改模块
   * 
   * @return
   */
  public Object modify(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    String name = r.get("name");
    String type = r.get("type");
    String url = r.get("url");
    String home = r.get("home");
    String content = r.get("content");
    int index = r.getInt("index");
    int pos = r.getInt("pos");
    Validator.check(name,new RuleNotEmpty("模块名"));
    IUpdater iup = Dao.getUpdater().table(Tables.MODULE);
    iup.where("C_ID",id);
    iup.set("C_NAME",name);
    iup.set("C_TYPE",type);
    iup.set("C_URL",url);
    iup.set("C_IS_HOME",home);
    iup.set("C_CONTENT",content);
    iup.set("C_INDEX",index);
    iup.set("C_POS",pos);
    iup.update();
    return new Tip("修改成功");
  }
  
  /**
   * 获取模块详情
   * @return
   */
  public Object detail(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    return new HideJson(moduleService.getModule(id));
  }

  /**
   * 删除模块
   * 
   * @return
   */
  public Object delete(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    moduleService.deleteModule(id);
    return new Tip("删除成功");
  }
}