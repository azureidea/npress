package nc.liat6.npress.init.impl;

import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.exception.DaoException;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.util.Dater;
import nc.liat6.frame.util.IOHelper;
import nc.liat6.npress.Tables;
import nc.liat6.npress.init.IInit;

/**
 * 配置初始化
 *
 * @author 6tail
 *
 */
public class InitConfig implements IInit{
  public void init(){
    try{
      if(Dao.getCounter().table(Tables.CONFIG).count()>0){
        return;
      }
    }catch(DaoException e){}
    ITrans t = null;
    try{
      t = TransFactory.getTrans();
      IInserter ins = t.getInserter();
      ins.table(Tables.CONFIG);
      //键
      ins.set("C_KEY","CACHE_ENABLE");
      //值
      ins.set("C_VALUE","false");
      //名称
      ins.set("C_NAME","开启缓存");
      //描述
      ins.set("C_DESC","为true时开启缓存功能。");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","WEB_NAME");
      ins.set("C_VALUE","blog by npress");
      ins.set("C_NAME","网站名");
      ins.set("C_DESC","");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","WEB_AUTHOR");
      ins.set("C_VALUE","六特尔");
      ins.set("C_NAME","作者");
      ins.set("C_DESC","");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","theme");
      ins.set("C_VALUE","basic");
      ins.set("C_NAME","主题");
      ins.set("C_DESC","默认为basic");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","WEB_KEY");
      ins.set("C_VALUE","npress,java开源,blog");
      ins.set("C_NAME","关键词");
      ins.set("C_DESC","");
      ins.insert();
      
      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","WEB_DESC");
      ins.set("C_VALUE","npress是六特尔基于NLF框架开发的一款java开源blog程序。");
      ins.set("C_NAME","描述");
      ins.set("C_DESC","");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","WEB_COPYRIGHT");
      ins.set("C_VALUE","Copyrights "+Dater.year(Dater.now())+" &copy; 6tail.cn");
      ins.set("C_NAME","版权声明");
      ins.set("C_DESC","");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","CACHE_DIR");
      ins.set("C_VALUE","cache");
      ins.set("C_NAME","缓存目录名");
      ins.set("C_DESC","一般情况下不建议修改");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","UPLOAD_DIR");
      ins.set("C_VALUE","uploads");
      ins.set("C_NAME","文件上传目录名");
      ins.set("C_DESC","一般情况下不建议修改");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","PIC_WIDTH");
      ins.set("C_VALUE","160");
      ins.set("C_NAME","缩略图宽度");
      ins.set("C_DESC","单位：像素");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","PIC_HEIGHT");
      ins.set("C_VALUE","160");
      ins.set("C_NAME","缩略图高度");
      ins.set("C_DESC","单位：像素");
      ins.insert();

      ins = t.getInserter();
      ins.table(Tables.CONFIG);
      ins.set("C_KEY","DOMAIN");
      ins.set("C_VALUE","localhost");
      ins.set("C_NAME","域名");
      ins.set("C_DESC","前面不要加http://，后面不要加/");
      ins.insert();
      t.commit();
    }finally{
      IOHelper.closeQuietly(t);
    }
  }
}