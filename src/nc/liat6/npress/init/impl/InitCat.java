package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.npress.Tables;
import nc.liat6.npress.init.IInit;

/**
 * 分类初始化
 *
 * @author 6tail
 *
 */
public class InitCat implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.CAT).count()>0){
        return;
      }
    }catch(DaoException e){}
    IInserter ins = Dao.getInserter();
    ins.table(Tables.CAT);
    //ID
    ins.set("C_ID",1);
    //名称
    ins.set("C_NAME","未分类");
    //类型：1显示，0隐藏
    ins.set("C_TYPE",1);
    ins.insert();
  }
}