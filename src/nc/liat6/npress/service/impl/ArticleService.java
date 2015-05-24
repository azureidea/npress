package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.paging.PageData;
import nc.liat6.frame.util.IOHelper;
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

  public PageData pageArticles(int pageNumber,int pageSize){
    PageData pd = Dao.getSelecter().table("T_ARTICLE").desc("C_ID").page(pageNumber,pageSize,Article.class,articleAdapter);
    List<?> l = pd.getData();
    for(int i = 0;i<l.size();i++){
      Article m = (Article)l.get(i);
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
    }
    return pd;
  }

  public Article getArticle(long id){
    Article m = Dao.getSelecter().table("T_ARTICLE").where("C_ID",id).one(Article.class,articleAdapter);
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

  public PageData pageByCat(long catId,int pageNumber,int pageSize){
    List<Bean> acs = Dao.getSelecter().table("T_ARTICLE_CAT").where("C_CAT_ID",catId).select();
    List<Long> artIds = new ArrayList<Long>(acs.size());
    for(Bean o:acs){
      long id = o.getLong("C_ARTICLE_ID",0);
      if(!artIds.contains(id)){
        artIds.add(id);
      }
    }
    PageData pd = Dao.getSelecter().table("T_ARTICLE").whereIn("C_ID",artIds.toArray()).desc("C_ID").page(pageNumber,pageSize,Article.class,articleAdapter);
    List<?> l = pd.getData();
    for(int i = 0;i<l.size();i++){
      Article m = (Article)l.get(i);
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
    }
    return pd;
  }

  public List<Cat> listCats(long id){
    List<Bean> l = Dao.getSelecter().table("T_ARTICLE_CAT").where("C_ARTICLE_ID",id).asc("C_ID").select();
    List<Cat> lc = Dao.getSelecter().table("T_CAT").select(Cat.class,catAdapter);
    List<Cat> cats = new ArrayList<Cat>(l.size());
    for(Bean o:l){
      for(Cat q:lc){
        if(q.getId()==o.getLong("C_CAT_ID",0)){
          cats.add(q);
        }
      }
    }
    return cats;
  }

  public PageData search(final String keywords,final int pageNumber,final int pageSize){
    PageData pd = Dao.getSelecter().table("T_ARTICLE").whereLike("C_TITLE",keywords).desc("C_ID").page(pageNumber,pageSize,Article.class,articleAdapter);
    List<?> l = pd.getData();
    for(int i = 0;i<l.size();i++){
      Article m = (Article)l.get(i);
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
    }
    return pd;
  }

  public void deleteArticle(long id){
    ITrans t = null;
    try{
      t = TransFactory.getTrans();
      t.getDeleter().table("T_ARTICLE").where("C_ID",id).delete();
      t.getDeleter().table("T_ARTICLE_CAT").where("C_ARTICLE_ID",id).delete();
      t.commit();
    }finally{
      IOHelper.closeQuietly(t);
    }
  }
}