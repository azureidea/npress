package nc.liat6.npress.admin;

import nc.liat6.frame.web.response.Page;

/**
 * 管理主界面
 * 
 * @author 6tail
 * 
 */
public class Main{

	/**
	 * 主界面
	 * @return
	 */
	public Object page(){
		Page p = new Page();
		p.setUri("/admin/main.jsp");
		return p;
	}

	/**
	 * 首页
	 * @return
	 */
	public Object home(){
		Page p = new Page("/admin/home.jsp");
		return p;
	}
}