package nc.liat6.npress;

import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.db.custom.csv.CsvDriver;
import nc.liat6.frame.db.custom.csv.CsvSetting;
import nc.liat6.frame.db.setting.IDbSetting;
import nc.liat6.frame.db.setting.IDbSettingManager;

/**
 * �Զ������ݿ�����
 * @author 6tail
 *
 */
public class DbSettingManagerNPress implements IDbSettingManager{

	@Override
	public List<IDbSetting> getDbSettings(){
		List<IDbSetting> l = new ArrayList<IDbSetting>();
		CsvSetting cs = new CsvSetting();
		cs.setAlias("blog");
		cs.setDbType("csv");
		cs.setDriver(CsvDriver.class.getName());
		cs.setDbName("D:\\db\\npress");
		cs.setType("csv");
		l.add(cs);
		return l;
	}

}
