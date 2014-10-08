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
    ins.set("C_PIC","");
    ins.set("C_CONTENT","<p>欢迎使用npress，这是初始化后自动生成的说明文档。</p><p>npress可以在无需数据库的情况下直接运行，它使用NLF框架自带的csv数据库，当然，你也可以在classes/db/下修改配置文件以使用其他数据库。</p><p>后台登录地址为：/login.jsp，默认管理员用户名：admin，默认密码：admin，请登录后台进行修改。</p><p>源代码github：<a target=\"_blank\" href=\"https://github.com/6tail/npress\">https://github.com/6tail/npress</a></p><p>还有一个需要注意的，在部署的时候，记得把js/icore.js中的debug改为false。</p>");
    ins.set("C_DESC","<p>欢迎使用npress，这是初始化后自动生成的说明文档。</p>npress可以在无需数据库的情况下直接运行，它使用NLF框架自带的csv数据库，当然，你也可以在classes/db/下修改配置文件");
    ins.set("C_DAY",Dater.ymd(Dater.now()));
    ins.set("C_TIME",Dater.ymdhms(Dater.now()));
    ins.set("C_KEYWORDS","");
    ins.insert();
    t.commit();
    t.close();
  }
}
