package nc.liat6.npress.service;

import java.util.List;
import nc.liat6.frame.paging.PageData;
import nc.liat6.npress.bean.Article;
import nc.liat6.npress.bean.Cat;

/**
 * 文章业务接口
 * 
 * @author 6tail
 * 
 */
public interface IArticleService{
  /**
   * 获取文章分页列表
   * 
   * @return 文章列表
   */
  PageData pageArticles(int pageNumber,int pageSize);

  /**
   * 按关键词搜索文章分页列表
   * 
   * @param keywords 关键词
   * @return 文章列表
   */
  PageData search(String keywords,int pageNumber,int pageSize);

  /**
   * 根据ID获取文章
   * 
   * @param id 文章ID
   * @return 文章
   */
  Article getArticle(long id);

  /**
   * 获取指定分类下的文章分页列表
   * 
   * @return 文章列表
   */
  PageData pageByCat(long catId,int pageNumber,int pageSize);

  /**
   * 获取文章所属分类列表
   * 
   * @param id 文章ID
   * @return 分类列表
   */
  List<Cat> listCats(long id);

  /**
   * 删除文章
   * 
   * @param id 文章ID
   */
  void deleteArticle(long id);
}