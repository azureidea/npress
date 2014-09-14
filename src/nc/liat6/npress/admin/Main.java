package nc.liat6.npress.admin;

import nc.liat6.frame.web.response.Page;

/**
 * 后台-管理主界面
 * 
 * @author 6tail
 * 
 */
public class Main{

  /**
   * 主界面
   * 
   * @return
   */
  public Object page(){
    return new Page("/admin/main.jsp");
  }

  /**
   * 首页
   * 
   * @return
   */
  public Object home(){
    return new Page("/admin/home.jsp");
  }
}
