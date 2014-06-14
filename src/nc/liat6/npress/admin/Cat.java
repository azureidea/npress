package nc.liat6.npress.admin;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.db.plugin.IDeleter;
import nc.liat6.frame.db.plugin.IInserter;
import nc.liat6.frame.db.plugin.IUpdater;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.util.ID;
import nc.liat6.frame.validate.Validator;
import nc.liat6.frame.validate.rule.RuleNotEmpty;
import nc.liat6.frame.web.response.Tip;

public class Cat{
	
	public Object add(){
		Request r = Context.get(Statics.REQUEST);
		String name = r.get("name");
		
		Validator.check(name,new RuleNotEmpty("分类名"));
		
		ITrans t = TransFactory.getTrans();
		if(t.getCounter().table("T_CAT").where("C_NAME",name).count()>0){
			t.rollback();
			t.close();
			throw new BadException("该分类名已存在");
		}
		
		long id = ID.next().longValue();
		IInserter ins = t.getInserter().table("T_CAT");
		ins.set("C_ID",id);
		ins.set("C_NAME",name);
		ins.insert();
		t.commit();
		t.close();
		
		nc.liat6.npress.bean.Cat cat = new nc.liat6.npress.bean.Cat();
		cat.setId(id);
		cat.setName(name);
		return new Tip(cat,"添加成功");
	}
	
	public Object modify(){
		Request r = Context.get(Statics.REQUEST);
		long id = r.getLong("id");
		String name = r.get("name");
		
		Validator.check(name,new RuleNotEmpty("分类名"));
		
		ITrans t = TransFactory.getTrans();
		if(t.getCounter().table("T_CAT").where("C_NAME",name).whereNq("C_ID",id).count()>0){
			t.rollback();
			t.close();
			throw new BadException("该分类名已存在");
		}
		
		IUpdater iup = t.getUpdater().table("T_CAT");
		iup.where("C_ID",id);
		iup.set("C_NAME",name);
		iup.update();
		t.commit();
		t.close();
		
		nc.liat6.npress.bean.Cat cat = new nc.liat6.npress.bean.Cat();
		cat.setId(id);
		cat.setName(name);
		return new Tip(cat,"修改成功");
	}
	
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
		
		IDeleter ide = t.getDeleter().table("T_CAT");
		ide.where("C_ID",id);
		ide.delete();
		t.commit();
		t.close();
		
		return new Tip("删除成功");
	}
	
}
