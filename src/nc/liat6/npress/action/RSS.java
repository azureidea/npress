package nc.liat6.npress.action;

import java.util.List;
import nc.liat6.frame.util.Dater;
import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.service.IArticleService;

/**
 * RSS
 * @author 6tail
 *
 */
public class RSS{

  /** 文章业务接口 */
  private IArticleService articleService;

  public void setArticleService(IArticleService articleService){
    this.articleService = articleService;
  }

  /**
   * RSS页面，最新发布的20条
   * @return
   */
  public Object page(){
    Page p = new Page("/rss.jsp");
    List<?> l = articleService.pageArticles(1,20).getData();
    String pubTime = Dater.ymdhms(Dater.now());
    if(l.size()>0){
      nc.liat6.npress.bean.Article o = (nc.liat6.npress.bean.Article)l.get(0);
      pubTime = o.getTime();
    }
    p.set("articles",l);
    p.set("pubTime",pubTime);
    p.deliver();
    return p;
  }
}
