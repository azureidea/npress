package nc.liat6.npress.action;

import javax.servlet.http.HttpSession;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.entity.Bean;
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

/**
 * ∫ÛÃ®µ«¬º
 * 
 * @author 6tail
 * 
 */
public class Login{

	public Object page(){
		Page p = new Page("login.jsp");
		return p;
	}

	public Object login(){
		Request r = Context.get(Statics.REQUEST);
		String account = r.get("account");
		String password = r.get("password",false);

		Validator.check(account,"account",new RuleNotEmpty("’À∫≈"));
		Validator.check(password,"password",new RuleNotEmpty("√‹¬Î"));

		ITrans t = TransFactory.getTrans();
		Bean user = null;
		try{
			user = t.getSelecter().table("T_USER").where("C_ACCOUNT",account).one();
		}catch(Exception e){
			return new Bad("’À∫≈√‹¬Î¥ÌŒÛ");
		}
		if(!password.equals(user.get("C_PASSWORD"))){
			return new Bad("’À∫≈√‹¬Î¥ÌŒÛ");
		}
		User u = new User();
		u.setAccount(user.getString("C_ACCOUNT",""));
		u.setPassword(user.getString("C_PASSWORD",""));
		u.setId(user.getLong("C_ID",0));
		u.setName(user.getString("C_NAME",""));
		u.setAdmin(user.getInt("C_IS_ADMIN",0));
		
		HttpSession session = r.find(WebExecute.TAG_SESSION);
		session.setAttribute(Global.SESSION_USER,u);
		return new Json(u.getAdmin());
	}
	
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
	
	public Object logout(){
		Request r = Context.get(Statics.REQUEST);
		HttpSession session = r.find(WebExecute.TAG_SESSION);
		session.removeAttribute(Global.SESSION_USER);
		return new Json();
	}

}
