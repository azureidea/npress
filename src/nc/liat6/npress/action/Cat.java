package nc.liat6.npress.action;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Paging;
import nc.liat6.npress.service.IArticleService;
import nc.liat6.npress.service.ICatService;
import nc.liat6.npress.service.IModuleService;

/**
 * 分类控制器
 * 
 * @author 6tail
 * 
 */
public class Cat{

  /** 模块业务接口 */
  private IModuleService moduleService;
  /** 分类业务接口 */
  private ICatService catService;
  /** 文章业务接口 */
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

  /**
   * 分类文章列表，带分页
   * @return
   */
  public Object page(){
    Request r = Context.get(Statics.REQUEST);
    long id = r.getLong("id");
    Paging p = new Paging("cat.jsp");
    p.setPageData(articleService.pageByCat(id,r.getPageNumber(),r.getPageSize()));
    p.set("modules",moduleService.listModules());
    p.set("cats",catService.listCats());
    p.set("cat",catService.getCat(id));
    p.deliver();
    return p;
  }
}
