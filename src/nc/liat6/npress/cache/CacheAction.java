package nc.liat6.npress.cache;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.web.response.Page;

/**
 * �����ļ�������
 * @author 6tail
 *
 */
public class CacheAction{
	
	/**
	 * ���ػ����ļ�ҳ��
	 * @return
	 */
	public Object getFile(){
		Request r = Context.get(Statics.REQUEST);
		String file = r.get("file");
		Page p = new Page();
		p.setUri("/cache/"+file);
		return p;
	}

}
