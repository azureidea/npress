package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

public class Cat{

	private IModuleService moduleService;
	private ICatService catService;
	private IArticleService articleService;

	public void setModuleService(IModuleService moduleService){
		this.moduleService = moduleService;
	}
	
	public void setArticleService(IArticleService articleService){
		this.articleService = articleService;
	}

	public void setCatService(ICatService catService){
		this.catService = catService;
	}

	public Object page(){
		Request r = Context.get(Statics.REQUEST);
		long id = r.getLong("id");
		Paging p = new Paging();
		p.setUri("cat.jsp");
		p.setPageData(articleService.pageByCat(id,r.getPageNumber(),r.getPageSize()));
		p.set("modules",moduleService.listModules());
		p.set("cats",catService.listCats());
		p.set("cat",catService.getCat(id));
		p.deliver();
		return p;
	}
	
}
