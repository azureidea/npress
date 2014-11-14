package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.dao.DaoAdapter;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.npress.bean.Cat;
import nc.liat6.npress.bean.adapter.CatAdapter;
import nc.liat6.npress.service.ICatService;

/**
 * 分类业务实现
 *
 * @author 6tail
 *
 */
public class CatService implements ICatService{

  /** Cat转换适配器 */
  private static final IBeanRule catAdapter = new CatAdapter();

  @Override
  public List<Cat> listCats(){
    List<Cat> cats = Dao.list(Cat.class,new DaoAdapter(){
      @Override
      public List<Bean> list(ITrans t){
        return t.getSelecter().table("T_CAT").asc("C_ID").select();
      }
      @Override
      public IBeanRule rule(){
        return catAdapter;
      }
    });
    List<Bean> lac = Dao.list(new DaoAdapter(){
      @Override
      public List<Bean> list(ITrans t){
        return t.getSelecter().table("T_ARTICLE_CAT").select();
      }
    });
    for(Cat m:cats){
      int n = 0;
      for(Bean q:lac){
        if(q.getLong("C_CAT_ID",0)==m.getId()){
          n++;
        }
      }
      m.setCount(n);
    }
    return cats;
  }

  @Override
  public Cat getCat(final long id){
    Cat cat = Dao.one(Cat.class,new DaoAdapter(){
      @Override
      public Bean one(ITrans t){
        return t.getSelecter().table("T_CAT").where("C_ID",id).one();
      }
      @Override
      public IBeanRule rule(){
        return catAdapter;
      }
    });
    return cat;
  }
}
