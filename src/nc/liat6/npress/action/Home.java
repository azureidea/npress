package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

/**
 * 首页
 * 
 * @author 6tail
 * 
 */
public class Home{

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

  /**
   * 文章列表，带分页
   * 
   * @return
   */
  public Object page(){
    Request r = Context.get(Statics.REQUEST);
    Paging p = new Paging("home.jsp");
    p.setPageData(articleService.pageArticles(r.getPageNumber(),r.getPageSize()));
    p.set("modules",moduleService.listModules());
    p.set("cats",catService.listCats());
    p.deliver();
    return p;
  }
}
