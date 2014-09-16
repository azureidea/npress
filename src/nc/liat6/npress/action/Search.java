package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

/**
 * 文章搜索
 * @author 6tail
 *
 */
public class Search{
  
  /** 模块业务接口 */
  private IModuleService moduleService;
  /** 文章业务接口 */
  private IArticleService articleService;
  /** 分类业务接口 */
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
  
  public Object result(){
    Request r = Context.get(Statics.REQUEST);
    String s = r.get("s");
    Paging p = new Paging("search.jsp");
    p.setPageData(articleService.search(s,r.getPageNumber(),r.getPageSize()));
    p.set("modules",moduleService.listModules());
    p.set("cats",catService.listCats());
    p.deliver();
    return p;
  }
  
}
