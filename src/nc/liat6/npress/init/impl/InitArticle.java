package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.util.Dater;
import nc.liat6.npress.init.IInit;

/**
 * 文章初始化
 * 
 * @author 6tail
 * 
 */
public class InitArticle implements IInit{

  @Override
  public void init(){
    ITrans t = TransFactory.getTrans();
    try{
      if(t.getCounter().table("T_ARTICLE").count()>0){
        t.rollback();
        t.close();
        return;
      }
    }catch(DaoException e){
    }
    IInserter ins = t.getInserter();
    ins.table("T_ARTICLE");
    ins.set("C_ID",1);
    ins.set("C_USER_ID",1);
    ins.set("C_TITLE","Hello World!");
    ins.set("C_CONTENT","<p>欢迎使用npress，这是初始化后自动生成的一篇文章。</p>");
    ins.set("C_DESC","<p>欢迎使用npress，这是初始化后自动生成的一篇文章。</p>");
    ins.set("C_DAY",Dater.ymd(Dater.now()));
    ins.set("C_TIME",Dater.ymdhms(Dater.now()));
    ins.insert();
    t.commit();
    t.close();
  }
}
