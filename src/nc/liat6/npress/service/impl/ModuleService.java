package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.bean.Module;
import nc.liat6.npress.bean.adapter.ModuleAdapter;
import nc.liat6.npress.service.IModuleService;

/**
 * 模块业务实现
 * 
 * @author 6tail
 * 
 */
public class ModuleService implements IModuleService{

  /** Module转换适配器 */
  private static final IBeanRule moduleAdapter = new ModuleAdapter();

  @Override
  public List<Module> listModules(){
    ITrans t = TransFactory.getTrans();
    List<Bean> l = t.getSelecter().table("T_MODULE").asc("C_INDEX","C_ID").select();
    t.rollback();
    t.close();
    List<Module> modules = new ArrayList<Module>(l.size());
    for(Bean o:l){
      Module m = o.toObject(Module.class,moduleAdapter);
      modules.add(m);
    }
    return modules;
  }

  @Override
  public Module getModule(long id){
    ITrans t = TransFactory.getTrans();
    Bean o = t.getSelecter().table("T_MODULE").where("C_ID",id).one();
    t.rollback();
    t.close();
    Module m = o.toObject(Module.class,moduleAdapter);
    return m;
  }
}
