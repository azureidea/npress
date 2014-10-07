package nc.liat6.npress.admin;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.response.Tip;

/**
 * 后台-分类管理
 *
 * @author 6tail
 *
 */
public class Cat{

  /**
   * 添加分类
   *
   * @return
   */
  public Object add(){
    Request r = Context.get(Statics.REQUEST);
    String name = r.get("name");
    int type = r.getInt("type");
    Validator.check(name,new RuleNotEmpty("分类名"));
    ITrans t = TransFactory.getTrans();
    if(t.getCounter().table("T_CAT").where("C_NAME",name).count()>0){
      t.rollback();
      t.close();
      throw new BadException("该分类名已存在");
    }
    long id = ID.next().longValue();
    t.getInserter().table("T_CAT").set("C_ID",id).set("C_NAME",name).set("C_TYPE",type).insert();
    t.commit();
    t.close();
    nc.liat6.npress.bean.Cat cat = new nc.liat6.npress.bean.Cat();
    cat.setId(id);
    cat.setType(type);
    cat.setName(name);
    return new Tip(cat,"添加成功");
  }

  /**
   * 修改分类
   *
   * @return
   */
  public Object modify(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    String name = r.get("name");
    int type = r.getInt("type");
    Validator.check(name,new RuleNotEmpty("分类名"));
    ITrans t = TransFactory.getTrans();
    if(t.getCounter().table("T_CAT").where("C_NAME",name).whereNq("C_ID",id).count()>0){
      t.rollback();
      t.close();
      throw new BadException("该分类名已存在");
    }
    t.getUpdater().table("T_CAT").where("C_ID",id).set("C_NAME",name).set("C_TYPE",type).update();
    t.commit();
    t.close();
    nc.liat6.npress.bean.Cat cat = new nc.liat6.npress.bean.Cat();
    cat.setId(id);
    cat.setName(name);
    cat.setType(type);
    return new Tip(cat,"修改成功");
  }

  /**
   * 删除分类
   *
   * @return
   */
  public Object delete(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    if(1==id){
      throw new BadException("默认分类不能删除");
    }
    ITrans t = TransFactory.getTrans();
    if(t.getCounter().table("T_ARTICLE_CAT").where("C_CAT_ID",id).count()>0){
      t.rollback();
      t.close();
      throw new BadException("该分类下有文章，无法删除");
    }
    t.getDeleter().table("T_CAT").where("C_ID",id).delete();
    t.commit();
    t.close();
    return new Tip("删除成功");
  }
}
