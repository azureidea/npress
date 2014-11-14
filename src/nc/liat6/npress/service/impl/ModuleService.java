package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.dao.DaoAdapter;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
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
    List<Module> modules = Dao.list(Module.class,new DaoAdapter(){
      @Override
      public List<Bean> list(ITrans t){
        return t.getSelecter().table("T_MODULE").asc("C_INDEX","C_ID").select();
      }
      @Override
      public IBeanRule rule(){
        return moduleAdapter;
      }
    });
    return modules;
  }

  @Override
  public Module getModule(final long id){
    Module m = Dao.one(Module.class,new DaoAdapter(){
      @Override
      public Bean one(ITrans t){
        return t.getSelecter().table("T_MODULE").where("C_ID",id).one();
      }
      @Override
      public IBeanRule rule(){
        return moduleAdapter;
      }
    });
    return m;
  }
}