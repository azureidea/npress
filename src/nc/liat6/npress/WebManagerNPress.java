package nc.liat6.npress;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.config.IWebConfig;
import nc.liat6.frame.web.config.WebManager;
import nc.liat6.frame.web.response.Page;

/**
 * WEB������
 * 
 * @author 6tail
 * 
 */
public class WebManagerNPress extends WebManager{

	/** ��վʹ�õ����� */
	private String theme;

	public WebManagerNPress(IWebConfig config){
		super(config);
		theme = config.getGlobalVars().get("theme") + "";
	}

	@Override
	public ClassMethod before(String path){
		// ƥ��·����package-package-Class/method
		if(!path.matches("[/].{1,}[-].{1,}[/]\\w{1,}")){
			return null;
		}
		String[] keys = path.split("/");

		// package-package.Class
		String klass = keys[1];

		// method
		String method = keys[2];

		// package-package-ClassתΪpackage.package.Class
		klass = klass.replace("-",".");

		ClassMethod cm = new ClassMethod();
		cm.setKlass(klass);
		cm.setMethod(method);

		Context.set("NPRESS_CLASS_METHOD",cm);
		
		ClassMethod ncm = new ClassMethod();
		ncm.setKlass(klass);
		ncm.setMethod(method);

		//���������action.��ͷ�����Զ��ض���nc.liat6.npress.action��
		if(klass.startsWith("action.")){
			ncm.setKlass("nc.liat6.npress."+klass);
		}
		//���������admin.��ͷ�����Զ��ض���nc.liat6.npress.admin��
		if(klass.startsWith("admin.")){
			ncm.setKlass("nc.liat6.npress."+klass);
		}
		return ncm;
	}

	@Override
	public void filter(){
		super.filter();
		Object r = Context.get(WebExecute.EXECUTE_RETURN);
		// ������ص���Page���Զ��ض��򵽵�ǰ������
		if(r instanceof Page){
			Page p = (Page)r;
			if(!p.getUri().startsWith("/")){
				p.setUri("/themes/" + theme + "/" + p.getUri());
			}
		}
	}

}
