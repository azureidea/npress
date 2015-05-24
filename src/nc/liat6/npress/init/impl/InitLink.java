package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.npress.Tables;
import nc.liat6.npress.init.IInit;

/**
 * 友情链接初始化
 * 
 * @author 6tail
 * 
 */
public class InitLink implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.LINK).count()>0){
        return;
      }
    }catch(DaoException e){}
    IInserter ins = Dao.getInserter();
    ins.table(Tables.LINK);
    //ID
    ins.set("C_ID",1);
    //名称
    ins.set("C_NAME","六特尔");
    //网址
    ins.set("C_URL","http://6tail.cn");
    //序号
    ins.set("C_INDEX",0);
    ins.insert();
  }
}