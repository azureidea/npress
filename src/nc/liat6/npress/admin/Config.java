package nc.liat6.npress.admin;

import java.util.Map;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.service.IConfigService;

/**
 * 后台-配置管理
 * 
 * @author 6tail
 * 
 */
public class Config{

  /** 配置业务接口 */
  private IConfigService configService;

  public void setConfigService(IConfigService configService){
    this.configService = configService;
  }


  /**
   * 列表页面
   * 
   * @return
   */
  public Object page(){
    Page p = new Page("/admin/config/page.jsp");
    p.set("configs",configService.listConfigs());
    return p;
  }

  

  /**
   * 更新配置
   * 
   * @return
   */
  public Object modify(){
    Request r = Context.get(Statics.REQUEST);
    Map<String,String> params = r.getParams();
    ITrans t = TransFactory.getTrans();
    for(String key:params.keySet()){
      IUpdater iup = t.getUpdater().table("T_CONFIG");
      iup.where("C_KEY",key);
      iup.set("C_VALUE",params.get(key));
      iup.update();
    }
    t.commit();
    t.close();
    configService.fresh();
    configService.updateToApplication();
    return new Tip("更新成功");
  }

}
