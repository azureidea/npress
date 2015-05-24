package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.npress.Tables;
import nc.liat6.npress.init.IInit;

/**
 * 用户表初始化
 * 
 * @author 6tail
 * 
 */
public class InitUser implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.USER).count()>0){
        return;
      }
    }catch(DaoException e){}
    IInserter ins = Dao.getInserter();
    ins.table(Tables.USER);
    //ID
    ins.set("C_ID",1);
    //用户名
    ins.set("C_ACCOUNT","admin");
    //姓名
    ins.set("C_NAME","默认管理员");
    //密码
    ins.set("C_PASSWORD","admin");
    //是否管理员
    ins.set("C_IS_ADMIN",1);
    ins.insert();
  }
}