package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.init.IInit;

/**
 * 文章所属分类
 * @author 6tail
 *
 */
public class InitArticleCat implements IInit{

	@Override
	public void init(){
		ITrans t = TransFactory.getTrans();
		try{
			if(t.getCounter().table("T_ARTICLE_CAT").count() > 0){
				t.rollback();
				t.close();
				return;
			}
		}catch(DaoException e){}

		IInserter ins = t.getInserter();
		ins.table("T_ARTICLE_CAT");
		ins.set("C_ID",1);
		ins.set("C_ARTICLE_ID",1);
		ins.set("C_CAT_ID",1);
		ins.insert();
		t.commit();
		t.close();
	}

}
