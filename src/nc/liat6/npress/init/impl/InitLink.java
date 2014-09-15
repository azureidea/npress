package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.init.IInit;

/**
 * 友情链接数据初始化
 * 
 * @author 6tail
 * 
 */
public class InitLink implements IInit{

  @Override
  public void init(){
    ITrans t = TransFactory.getTrans();
    try{
      if(t.getCounter().table("T_LINK").count()>0){
        t.rollback();
        t.close();
        return;
      }
    }catch(DaoException e){
    }
    IInserter ins = t.getInserter();
    ins.table("T_LINK");
    ins.set("C_ID",1);
    ins.set("C_NAME","六特尔博客");
    ins.set("C_URL","http://6tail.cn");
    ins.set("C_INDEX",0);
    ins.insert();
    t.commit();
    t.close();
  }
}
