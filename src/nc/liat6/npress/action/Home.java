package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

/**
 * Ê×Ò³
 * 
 * @author 6tail
 * 
 */
public class Home{

	private IModuleService moduleService;
	private IArticleService articleService;
	private ICatService catService;

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
		Paging p = new Paging();
		p.setPageData(articleService.pageArticles(r.getPageNumber(),r.getPageSize()));
		p.setUri("home.jsp");
		p.set("modules",moduleService.listModules());
		p.set("cats",catService.listCats());
		p.deliver();
		return p;
	}

}
