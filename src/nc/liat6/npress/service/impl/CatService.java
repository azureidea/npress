package nc.liat6.npress.service.impl;

import java.util.List;
import nc.liat6.frame.db.Dao;
import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.entity.IBeanRule;
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

  public List<Cat> listCats(){
    List<Cat> cats = Dao.getSelecter().table("T_CAT").asc("C_ID").select(Cat.class,catAdapter);
    List<Bean> lac = Dao.getSelecter().table("T_ARTICLE_CAT").select();
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

  public Cat getCat(long id){
    return Dao.getSelecter().table("T_CAT").where("C_ID",id).one(Cat.class,catAdapter);
  }
}