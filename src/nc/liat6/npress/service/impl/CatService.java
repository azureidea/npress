package nc.liat6.npress.service.impl;

import java.util.ArrayList;
import java.util.List;

import nc.liat6.frame.db.entity.Bean;
import nc.liat6.frame.db.transaction.ITrans;
import nc.liat6.frame.db.transaction.TransFactory;
import nc.liat6.npress.bean.Cat;
import nc.liat6.npress.service.ICatService;

/**
 * 分类业务实现
 * @author 6tail
 *
 */
public class CatService implements ICatService{

	@Override
	public List<Cat> listCats(){
		ITrans t = TransFactory.getTrans();
		List<Bean> l = t.getSelecter().table("T_CAT").asc("C_ID").select();
		List<Bean> lac = t.getSelecter().table("T_ARTICLE_CAT").select();
		t.rollback();
		t.close();
		List<Cat> cats = new ArrayList<Cat>(l.size());
		for(Bean o:l){
			Cat m = new Cat();
			m.setId(o.getLong("C_ID",0));
			m.setName(o.getString("C_NAME",""));
			int n = 0;
			for(Bean q:lac){
				if(q.getLong("C_CAT_ID",0)==m.getId()){
					n++;
				}
			}
			m.setCount(n);
			cats.add(m);
		}
		return cats;
	}

	@Override
	public Cat getCat(long id){
		ITrans t = TransFactory.getTrans();
		Bean o = t.getSelecter().table("T_CAT").where("C_ID",id).one();
		t.rollback();
		t.close();
		Cat m = new Cat();
		m.setId(o.getLong("C_ID",0));
		m.setName(o.getString("C_NAME",""));
		return m;
	}

}
