package nc.liat6.npress.aop;

import java.lang.reflect.Method;
import javax.servlet.http.HttpSession;
import nc.liat6.frame.aop.AopBeforeManager;
import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.npress.Global;
import nc.liat6.npress.bean.User;
import nc.liat6.npress.exception.UserLoginException;

/**
 * 后台登录验证
 * 
 * @author 6tail
 *
 */
public class LoginAop extends AopBeforeManager{
  public LoginAop(){
    super("nc.liat6.npress.admin.*","*");
  }
  
  @Override
  public void execute(Object o,Method m,Object[] args){
    Request r = Context.get(Statics.REQUEST);
    HttpSession session = r.find(Statics.FIND_SESSION);
    User user = (User)session.getAttribute(Global.SESSION_USER);
    if(null==user||1!=user.getAdmin()){
      throw new UserLoginException();
    }
  }
}