package nc.liat6.npress.action;

import javax.servlet.http.HttpSession;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.response.Bad;
import nc.liat6.frame.web.response.Json;
import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.Global;
import nc.liat6.npress.bean.User;
import nc.liat6.npress.bean.adapter.UserAdapter;

/**
 * 用户登录
 * 
 * @author 6tail
 * 
 */
public class Login{

  /** User转换适配器 */
  private static final IBeanRule userAdapter = new UserAdapter();

  /**
   * 登录页
   * 
   * @return
   */
  public Object page(){
    return new Page("login.jsp");
  }

  /**
   * 登录验证
   * 
   * @return
   */
  public Object login(){
    Request r = Context.get(Statics.REQUEST);
    String account = r.get("account");
    String password = r.get("password",false);
    Validator.check(account,new RuleNotEmpty("账号"));
    Validator.check(password,new RuleNotEmpty("密码"));
    ITrans t = TransFactory.getTrans();
    Bean u = null;
    try{
      u = t.getSelecter().table("T_USER").where("C_ACCOUNT",account).one();
    }catch(Exception e){
      return new Bad("账号密码错误");
    }
    User user = u.toObject(User.class,userAdapter);
    if(!password.equals(user.getPassword())){
      return new Bad("账号密码错误");
    }
    HttpSession session = r.find(WebExecute.TAG_SESSION);
    session.setAttribute(Global.SESSION_USER,user);
    return new Json(user.getAdmin());
  }

  /**
   * 检查是否已登录
   * 
   * @return
   */
  public Object check(){
    Request r = Context.get(Statics.REQUEST);
    HttpSession session = r.find(WebExecute.TAG_SESSION);
    User u = (User)session.getAttribute(Global.SESSION_USER);
    if(null==u){
      return new Json(null,null,false);
    }
    User safeUser = new User();
    safeUser.setName(u.getName());
    return new Json(safeUser);
  }

  /**
   * 退出登录
   * 
   * @return
   */
  public Object logout(){
    Request r = Context.get(Statics.REQUEST);
    HttpSession session = r.find(WebExecute.TAG_SESSION);
    session.removeAttribute(Global.SESSION_USER);
    return new Json();
  }
}
