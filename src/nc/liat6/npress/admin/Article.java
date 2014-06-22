package nc.liat6.npress.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.util.Dater;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.response.Page;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.frame.web.response.Tip;
import nc.liat6.npress.Global;
import nc.liat6.npress.bean.User;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;

public class Article{
	
	private ICatService catService;
	private IArticleService articleService;
	
	public void setCatService(ICatService catService){
		this.catService = catService;
	}
	
	public void setArticleService(IArticleService articleService){
		this.articleService = articleService;
	}

	public Object pageAdd(){
		Page p = new Page();
		p.setUri("/admin/article/add.jsp");
		p.set("cats",catService.listCats());
		return p;
	}
	
	public Object pageModify(){
		Request r = Context.get(Statics.REQUEST);
		long id = r.getLong("id");
		Page p = new Page();
		p.setUri("/admin/article/modify.jsp");
		p.set("art",articleService.getArticle(id));
		p.set("cats",catService.listCats());
		p.set("artCats",articleService.listCats(id));
		p.deliver();
		return p;
	}
	
	public Object pageList(){
		Request r = Context.get(Statics.REQUEST);
		Paging p = new Paging();
		p.setPageData(articleService.pageArticles(r.getPageNumber(),r.getPageSize()));
		p.setUri("/admin/article/list.jsp");
		p.deliver();
		return p;
	}
	
	public Object add(){
		Request r = Context.get(Statics.REQUEST);
		String title = r.get("title");
		String desc = r.get("desc");
		String content = r.get("content");
		String cats = r.get("cats");
		
		List<String> l = new ArrayList<String>();
		String[] cs = cats.split(",");
		for(String s:cs){
			s = s.trim();
			if(!s.equals("")){
				l.add(s);
			}
		}
		
		Validator.check(title,new RuleNotEmpty("����"));
		
		HttpSession session = r.find(WebExecute.TAG_SESSION);
		User user = (User)session.getAttribute(Global.SESSION_USER);
		
		ITrans t = TransFactory.getTrans();
		String id = ID.next()+"";
		IInserter ins = t.getInserter().table("T_ARTICLE");
		ins.set("C_ID",id);
		ins.set("C_TITLE",title);
		ins.set("C_DESC",desc);
		ins.set("C_USER_ID",user.getId());
		ins.set("C_CONTENT",content);
		ins.set("C_TIME",Dater.ymdhms(Dater.now()));
		ins.set("C_DAY",Dater.ymd(Dater.now()));
		ins.insert();
		
		if(l.size()>0){
			for(String cat:l){
				t.getInserter().table("T_ARTICLE_CAT").set("C_ID",ID.next()).set("C_ARTICLE_ID",id).set("C_CAT_ID",cat).insert();
			}
		}else{
			t.getInserter().table("T_ARTICLE_CAT").set("C_ID",ID.next()).set("C_ARTICLE_ID",id).set("C_CAT_ID",1).insert();
		}
		t.commit();
		t.close();
		return new Tip("�����ɹ�");
	}
	
	public Object modify(){
		Request r = Context.get(Statics.REQUEST);
		long id = r.getLong("id");
		String title = r.get("title");
		String desc = r.get("desc");
		String content = r.get("content");
		
		Validator.check(title,new RuleNotEmpty("����"));
		
		HttpSession session = r.find(WebExecute.TAG_SESSION);
		User user = (User)session.getAttribute(Global.SESSION_USER);
		
		Logger.getLog().info(desc);
		
		ITrans t = TransFactory.getTrans();
		IUpdater iup = t.getUpdater().table("T_ARTICLE");
		iup.where("C_ID",id);
		iup.set("C_TITLE",title);
		iup.set("C_DESC",desc);
		iup.set("C_USER_ID",user.getId());
		iup.set("C_CONTENT",content);
		iup.update();
		
		t.commit();
		t.close();
		return new Tip("�޸ĳɹ�");
	}
	
	public Object setCat(){
		Request r = Context.get(Statics.REQUEST);
		long id = r.getLong("id");
		String cats = r.get("cats");
		
		List<String> l = new ArrayList<String>();
		String[] cs = cats.split(",");
		for(String s:cs){
			s = s.trim();
			if(!s.equals("")){
				l.add(s);
			}
		}
		
		ITrans t = TransFactory.getTrans();
		t.getDeleter().table("T_ARTICLE_CAT").where("C_ARTICLE_ID",id).delete();
		if(l.size()>0){
			for(String cat:l){
				t.getInserter().table("T_ARTICLE_CAT").set("C_ID",ID.next()).set("C_ARTICLE_ID",id).set("C_CAT_ID",cat).insert();
			}
		}else{
			t.getInserter().table("T_ARTICLE_CAT").set("C_ID",ID.next()).set("C_ARTICLE_ID",id).set("C_CAT_ID",1).insert();
		}
		
		t.commit();
		t.close();
		return new Tip("�޸ĳɹ�");
	}
	
}