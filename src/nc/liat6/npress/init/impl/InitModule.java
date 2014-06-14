package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.bean.Module;
import nc.liat6.npress.init.IInit;

/**
 * ģ���ʼ��
 * @author 6tail
 *
 */
public class InitModule implements IInit{

	@Override
	public void init(){
		ITrans t = TransFactory.getTrans();
		try{
			if(t.getCounter().table("T_MODULE").count() > 0){
				t.rollback();
				t.close();
				return;
			}
		}catch(DaoException e){}

		IInserter ins = t.getInserter();
		ins.table("T_MODULE");
		ins.set("C_ID",1);
		ins.set("C_NAME","��ҳ");
		ins.set("C_TYPE",Module.TYPE_IN_SELF_URL);
		ins.set("C_URL","action-Home/page");
		ins.set("C_CONTENT","");
		ins.set("C_IS_HOME",1);
		ins.set("C_INDEX",0);
		ins.insert();
		
		ins = t.getInserter();
		ins.table("T_MODULE");
		ins.set("C_ID",2);
		ins.set("C_NAME","������");
		ins.set("C_TYPE",Module.TYPE_SELF_PAGE);
		ins.set("C_URL","");
		ins.set("C_CONTENT","about me");
		ins.set("C_IS_HOME",0);
		ins.set("C_INDEX",10);
		ins.insert();
		t.commit();
		t.close();
	}

}
