package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.init.IInit;

/**
 * 分类初始化
 * @author 6tail
 *
 */
public class InitCat implements IInit{

	@Override
	public void init(){
		ITrans t = TransFactory.getTrans();
		try{
			if(t.getCounter().table("T_CAT").count() > 0){
				t.rollback();
				t.close();
				return;
			}
		}catch(DaoException e){}

		IInserter ins = t.getInserter();
		ins.table("T_CAT");
		ins.set("C_ID",1);
		ins.set("C_NAME","未分类");
		ins.insert();
		t.commit();
		t.close();
	}

}
