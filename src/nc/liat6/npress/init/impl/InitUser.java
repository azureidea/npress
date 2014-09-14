package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.init.IInit;

/**
 * 用户数据初始化
 * 
 * @author 6tail
 * 
 */
public class InitUser implements IInit{

  @Override
  public void init(){
    ITrans t = TransFactory.getTrans();
    try{
      if(t.getCounter().table("T_USER").count()>0){
        t.rollback();
        t.close();
        return;
      }
    }catch(DaoException e){
    }
    IInserter ins = t.getInserter();
    ins.table("T_USER");
    ins.set("C_ID",1);
    ins.set("C_ACCOUNT","admin");
    ins.set("C_NAME","默认管理员");
    ins.set("C_PASSWORD","admin");
    ins.set("C_IS_ADMIN",1);
    ins.insert();
    t.commit();
    t.close();
  }
}
