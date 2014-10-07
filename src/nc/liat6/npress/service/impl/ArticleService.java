package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.paging.PageData;
import nc.liat6.npress.bean.Article;
import nc.liat6.npress.bean.Cat;
import nc.liat6.npress.bean.adapter.ArticleAdapter;
import nc.liat6.npress.bean.adapter.CatAdapter;
import nc.liat6.npress.service.IArticleService;

/**
 * 文章业务实现
 *
 * @author 6tail
 *
 */
public class ArticleService implements IArticleService{

  /** Article转换适配器 */
  private static final IBeanRule articleAdapter = new ArticleAdapter();

  /** Cat转换适配器 */
  private static final IBeanRule catAdapter = new CatAdapter();

  @Override
  public PageData pageArticles(int pageNumber,int pageSize){
    ITrans t = TransFactory.getTrans();
    PageData pd = t.getSelecter().table("T_ARTICLE").desc("C_ID").page(pageNumber,pageSize);
    t.rollback();
    t.close();
    List<?> l = pd.getData();
    List<Article> articles = new ArrayList<Article>(l.size());
    for(int i = 0;i<l.size();i++){
      Bean o = pd.getBean(i);
      Article m = o.toObject(Article.class,articleAdapter);
      List<Cat> cats = listCats(m.getId());
      for(Cat cat:cats){
        switch(cat.getType()){
          case 2:
            m.addTag(cat);
            break;
          default:
            m.addCat(cat);
        }
      }
      articles.add(m);
    }
    pd.setData(articles);
    return pd;
  }

  @Override
  public Article getArticle(long id){
    ITrans t = TransFactory.getTrans();
    Bean o = t.getSelecter().table("T_ARTICLE").where("C_ID",id).one();
    t.rollback();
    t.close();
    Article m = o.toObject(Article.class,articleAdapter);
    List<Cat> cats = listCats(m.getId());
    for(Cat cat:cats){
      switch(cat.getType()){
        case 2:
          m.addTag(cat);
          break;
        default:
          m.addCat(cat);
      }
    }
    return m;
  }

  @Override
  public PageData pageByCat(long catId,int pageNumber,int pageSize){
    ITrans t = TransFactory.getTrans();
    List<Bean> acs = t.getSelecter().table("T_ARTICLE_CAT").where("C_CAT_ID",catId).select();
    List<Long> artIds = new ArrayList<Long>(acs.size());
    for(Bean o:acs){
      long id = o.getLong("C_ARTICLE_ID",0);
      if(!artIds.contains(id)){
        artIds.add(id);
      }
    }
    PageData pd = t.getSelecter().table("T_ARTICLE").whereIn("C_ID",artIds.toArray()).desc("C_ID").page(pageNumber,pageSize);
    t.rollback();
    t.close();
    List<?> l = pd.getData();
    List<Article> articles = new ArrayList<Article>(l.size());
    for(int i = 0;i<l.size();i++){
      Bean o = pd.getBean(i);
      Article m = o.toObject(Article.class,articleAdapter);
      List<Cat> cats = listCats(m.getId());
      for(Cat cat:cats){
        switch(cat.getType()){
          case 2:
            m.addTag(cat);
            break;
          default:
            m.addCat(cat);
        }
      }
      articles.add(m);
    }
    pd.setData(articles);
    return pd;
  }

  @Override
  public List<Cat> listCats(long id){
    ITrans t = TransFactory.getTrans();
    List<Bean> l = t.getSelecter().table("T_ARTICLE_CAT").where("C_ARTICLE_ID",id).asc("C_ID").select();
    List<Bean> lc = t.getSelecter().table("T_CAT").select();
    List<Cat> cats = new ArrayList<Cat>(l.size());
    for(Bean o:l){
      for(Bean q:lc){
        if(q.getLong("C_ID",0)==o.getLong("C_CAT_ID",0)){
          Cat m = q.toObject(Cat.class,catAdapter);
          cats.add(m);
        }
      }
    }
    return cats;
  }

  @Override
  public PageData search(String keywords,int pageNumber,int pageSize){
    ITrans t = TransFactory.getTrans();
    PageData pd = t.getSelecter().table("T_ARTICLE").whereLike("C_TITLE",keywords).desc("C_ID").page(pageNumber,pageSize);
    t.rollback();
    t.close();
    List<?> l = pd.getData();
    List<Article> articles = new ArrayList<Article>(l.size());
    for(int i = 0;i<l.size();i++){
      Bean o = pd.getBean(i);
      Article m = o.toObject(Article.class,articleAdapter);
      List<Cat> cats = listCats(m.getId());
      for(Cat cat:cats){
        switch(cat.getType()){
          case 2:
            m.addTag(cat);
            break;
          default:
            m.addCat(cat);
        }
      }
      articles.add(m);
    }
    pd.setData(articles);
    return pd;
  }
}
