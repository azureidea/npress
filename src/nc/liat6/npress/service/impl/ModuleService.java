package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.npress.Tables;
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

  public List<Module> listModules(){
    return Dao.getSelecter().table(Tables.MODULE).asc("C_POS","C_INDEX","C_ID").select(Module.class,moduleAdapter);
  }

  public Module getModule(long id){
    return Dao.getSelecter().table(Tables.MODULE).where("C_ID",id).one(Module.class,moduleAdapter);
  }

  public void deleteModule(long id){
    Dao.getDeleter().table(Tables.MODULE).where("C_ID",id).delete();
  }
}