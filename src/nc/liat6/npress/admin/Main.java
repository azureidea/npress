package nc.liat6.npress.admin;

import nc.liat6.frame.web.response.Page;

/**
 * ����������
 * 
 * @author 6tail
 * 
 */
public class Main{

	/**
	 * ������
	 * @return
	 */
	public Object page(){
		Page p = new Page();
		p.setUri("/admin/main.jsp");
		return p;
	}

	/**
	 * ��ҳ
	 * @return
	 */
	public Object home(){
		Page p = new Page("/admin/home.jsp");
		return p;
	}
}