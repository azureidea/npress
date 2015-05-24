package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.npress.Tables;
import nc.liat6.npress.init.IInit;

/**
 * 文章所属分类初始化
 * 
 * @author 6tail
 * 
 */
public class InitArticleCat implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.ARTICLE_CAT).count()>0){
        return;
      }
    }catch(DaoException e){}
    IInserter ins = Dao.getInserter();
    ins.table(Tables.ARTICLE_CAT);
    //ID
    ins.set("C_ID",1);
    //文章ID
    ins.set("C_ARTICLE_ID",1);
    //分类ID
    ins.set("C_CAT_ID",1);
    ins.insert();
  }
}