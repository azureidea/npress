package nc.liat6.npress.service;

import java.util.List;

import nc.liat6.frame.paging.PageData;
import nc.liat6.npress.bean.Article;
import nc.liat6.npress.bean.Cat;

/**
 * ����ҵ��ӿ�
 * @author 6tail
 *
 */
public interface IArticleService{
	
	/**
	 * ��ȡ���·�ҳ�б�
	 * @return �����б�
	 */
	public PageData pageArticles(int pageNumber,int pageSize);
	
	/**
	 * ����ID��ȡ����
	 * @param id ����ID
	 * @return ����
	 */
	public Article getArticle(long id);
	
	/**
	 * ��ȡָ�������µ����·�ҳ�б�
	 * @return �����б�
	 */
	public PageData pageByCat(long catId,int pageNumber,int pageSize);
	
	public List<Cat> listCats(long id);

}
