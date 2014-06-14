package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.bean.Module;
import nc.liat6.npress.service.IModuleService;

/**
 * 模块业务实现
 * @author 6tail
 *
 */
public class ModuleService implements IModuleService{

	@Override
	public List<Module> listModules(){
		ITrans t = TransFactory.getTrans();
		List<Bean> l = t.getSelecter().table("T_MODULE").asc("C_INDEX","C_ID").select();
		t.rollback();
		t.close();
		List<Module> modules = new ArrayList<Module>(l.size());
		for(Bean o:l){
			Module m = new Module();
			m.setId(o.getLong("C_ID",0));
			m.setName(o.getString("C_NAME",""));
			m.setType(o.getInt("C_TYPE",0));
			m.setUrl(o.getString("C_URL",""));
			m.setHome(o.getInt("C_IS_HOME",0));
			m.setIndex(o.getInt("C_INDEX",0));
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
		Module m = new Module();
		m.setId(o.getLong("C_ID",0));
		m.setName(o.getString("C_NAME",""));
		m.setType(o.getInt("C_TYPE",0));
		m.setUrl(o.getString("C_URL",""));
		m.setHome(o.getInt("C_IS_HOME",0));
		m.setContent(o.getString("C_CONTENT",""));
		m.setIndex(o.getInt("C_INDEX",0));
		return m;
	}
	
}
