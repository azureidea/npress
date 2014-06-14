package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.frame.paging.PageData;
import nc.liat6.npress.bean.Article;
import nc.liat6.npress.bean.Cat;
import nc.liat6.npress.service.IArticleService;

/**
 * 文章业务实现
 * @author 6tail
 *
 */
public class ArticleService implements IArticleService{

	@Override
	public PageData pageArticles(int pageNumber,int pageSize){
		ITrans t = TransFactory.getTrans();
		PageData pd = t.getSelecter().table("T_ARTICLE").desc("C_ID").page(pageNumber,pageSize);
		t.rollback();
		t.close();
		List<?> l = pd.getData();
		List<Article> articles = new ArrayList<Article>(l.size());
		for(int i=0;i<l.size();i++){
			Bean o = pd.getBean(i);
			Article m = new Article();
			m.setId(o.getLong("C_ID",0));
			m.setTime(o.getString("C_TIME",""));
			m.setTitle(o.getString("C_TITLE",""));
			m.setContent(o.getString("C_CONTENT",""));
			m.setDescription(o.getString("C_DESC",""));
			m.setDay(o.getString("C_DAY",""));
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
		Article m = new Article();
		m.setId(o.getLong("C_ID",0));
		m.setTime(o.getString("C_TIME",""));
		m.setTitle(o.getString("C_TITLE",""));
		m.setContent(o.getString("C_CONTENT",""));
		m.setDescription(o.getString("C_DESC",""));
		m.setDay(o.getString("C_DAY",""));
		return m;
	}

	@Override
	public PageData pageByCat(long catId,int pageNumber,int pageSize){
		ITrans t = TransFactory.getTrans();
		PageData pd = t.getSelecter().table("T_ARTICLE_CAT").where("C_CAT_ID",catId).asc("C_ID").page(pageNumber,pageSize);
		List<Long> artIds = new ArrayList<Long>(pd.getSize());
		for(int i=0;i<pd.getSize();i++){
			Bean o = pd.getBean(i);
			artIds.add(o.getLong("C_ARTICLE_ID",0));
		}
		pd = t.getSelecter().table("T_ARTICLE").whereIn("C_ID",artIds.toArray()).asc("C_ID").page(1,pd.getSize());
		t.rollback();
		t.close();
		List<?> l = pd.getData();
		List<Article> articles = new ArrayList<Article>(l.size());
		for(int i=0;i<l.size();i++){
			Bean o = pd.getBean(i);
			Article m = new Article();
			m.setId(o.getLong("C_ID",0));
			m.setTime(o.getString("C_TIME",""));
			m.setTitle(o.getString("C_TITLE",""));
			m.setContent(o.getString("C_CONTENT",""));
			m.setDescription(o.getString("C_DESC",""));
			m.setDay(o.getString("C_DAY",""));
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
					Cat m = new Cat();
					m.setId(q.getLong("C_ID",0));
					m.setName(q.getString("C_NAME",""));
					cats.add(m);
				}
			}
		}
		return cats;
	}

}
