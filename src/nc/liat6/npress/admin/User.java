package nc.liat6.npress.admin;

import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.response.HideJson;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.Tables;
import nc.liat6.npress.bean.adapter.UserAdapter;

public class User{
  private static final IBeanRule userAdapter = new UserAdapter();
  public Object pageAdmin(){
    Request r = Context.get(Statics.REQUEST);
    ITrans t = TransFactory.getTrans();
    PageData pd = t.getSelecter().column("C_ID","C_NAME","C_ACCOUNT").table("T_USER").where("C_IS_ADMIN",1).page(r.getPageNumber(),r.getPageSize());
    t.rollback();
    t.close();
    List<?> l = pd.getData();
    List<nc.liat6.npress.bean.User> users = new ArrayList<nc.liat6.npress.bean.User>(l.size());
    for(int i = 0;i<l.size();i++){
      Bean o = pd.getBean(i);
      nc.liat6.npress.bean.User m = new nc.liat6.npress.bean.User();
      m.setId(o.getLong("C_ID",0));
      m.setAccount(o.getString("C_ACCOUNT",""));
      m.setName(o.getString("C_NAME",""));
      users.add(m);
    }
    pd.setData(users);
    Paging p = new Paging();
    p.setPageData(pd);
    p.setUri("/admin/user/admin.jsp");
    p.deliver();
    return p;
  }
  
  public Object detail(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    nc.liat6.npress.bean.User user = Dao.getSelecter().table(Tables.USER).where("C_ID",id).one(nc.liat6.npress.bean.User.class,userAdapter);
    user.setPassword(null);
    return new HideJson(user);
  }

  public Object modify(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    String account = r.get("account");
    String name = r.get("name");
    Validator.check(account,new RuleNotEmpty("用户名"));
    Validator.check(name,new RuleNotEmpty("姓名"));
    ITrans t = TransFactory.getTrans();
    if(t.getCounter().table("T_USER").where("C_ACCOUNT",account).whereNq("C_ID",id).count()>0){
      t.rollback();
      t.close();
      throw new BadException("该用户名已存在");
    }
    IUpdater iup = t.getUpdater().table("T_USER");
    iup.where("C_ID",id);
    iup.set("C_ACCOUNT",account);
    iup.set("C_NAME",name);
    iup.update();
    t.commit();
    t.close();
    return new Tip("修改成功");
  }

  public Object modifyPwd(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    String opd = r.get("opd",false);
    String npd = r.get("npd",false);
    String npd1 = r.get("npd1",false);
    Validator.check(opd,"opd",new RuleNotEmpty("原密码"));
    Validator.check(npd,"npd",new RuleNotEmpty("新密码"));
    Validator.check(npd1,"npd1",new RuleNotEmpty("新密码"));
    if(!npd.equals(npd1)){
      throw new BadException("两次输入的新密码不同！");
    }
    ITrans t = TransFactory.getTrans();
    Bean user = t.getSelecter().table("T_USER").where("C_ID",id).one();
    if(!user.get("C_PASSWORD").equals(opd)){
      throw new BadException("原密码输入有误！");
    }
    t.getUpdater().table("T_USER").set("C_PASSWORD",npd).where("C_ID",user.get("C_ID")).update();
    t.commit();
    t.close();
    return new Tip("修改成功！");
  }
}
