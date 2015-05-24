package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.util.IOHelper;
import nc.liat6.npress.Tables;
import nc.liat6.npress.bean.Module;
import nc.liat6.npress.init.IInit;

/**
 * 模块初始化
 *
 * @author 6tail
 *
 */
public class InitModule implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.MODULE).count()>0){
        return;
      }
    }catch(DaoException e){}
    ITrans t = null;
    try{
      t = TransFactory.getTrans();
      IInserter ins = t.getInserter();
      ins.table(Tables.MODULE);
      //ID
      ins.set("C_ID",1);
      //名称
      ins.set("C_NAME","首页");
      //类型
      ins.set("C_TYPE",Module.TYPE_IN_SELF_URL);
      //URL
      ins.set("C_URL","action-Home/page");
      //内容
      ins.set("C_CONTENT","");
      //是否首页
      ins.set("C_IS_HOME",1);
      //序号
      ins.set("C_INDEX",0);
      //位置
      ins.set("C_POS",Module.POS_TOP);
      ins.insert();
      ins = t.getInserter();
      ins.table(Tables.MODULE);
      ins.set("C_ID",2);
      ins.set("C_NAME","RSS");
      ins.set("C_TYPE",Module.TYPE_IN_BLANK_URL);
      ins.set("C_URL","action-RSS/page");
      ins.set("C_CONTENT","");
      ins.set("C_IS_HOME",0);
      ins.set("C_INDEX",0);
      ins.set("C_POS",Module.POS_BOTTOM);
      ins.insert();
      t.commit();
    }finally{
      IOHelper.closeQuietly(t);
    }
  }
}