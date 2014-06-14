package nc.liat6.npress;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.config.IWebConfig;
import nc.liat6.frame.web.config.WebManager;
import nc.liat6.frame.web.response.Page;

/**
 * WEB管理器
 * 
 * @author 6tail
 * 
 */
public class WebManagerNPress extends WebManager{

	/** 网站使用的主题 */
	private String theme;

	public WebManagerNPress(IWebConfig config){
		super(config);
		theme = config.getGlobalVars().get("theme") + "";
	}

	@Override
	public ClassMethod before(String path){
		// 匹配路径，package-package-Class/method
		if(!path.matches("[/].{1,}[-].{1,}[/]\\w{1,}")){
			return null;
		}
		String[] keys = path.split("/");

		// package-package.Class
		String klass = keys[1];

		// method
		String method = keys[2];

		// package-package-Class转为package.package.Class
		klass = klass.replace("-",".");

		ClassMethod cm = new ClassMethod();
		cm.setKlass(klass);
		cm.setMethod(method);

		Context.set("NPRESS_CLASS_METHOD",cm);
		
		ClassMethod ncm = new ClassMethod();
		ncm.setKlass(klass);
		ncm.setMethod(method);

		//如果请求以action.开头，则自动重定向到nc.liat6.npress.action包
		if(klass.startsWith("action.")){
			ncm.setKlass("nc.liat6.npress."+klass);
		}
		//如果请求以admin.开头，则自动重定向到nc.liat6.npress.admin包
		if(klass.startsWith("admin.")){
			ncm.setKlass("nc.liat6.npress."+klass);
		}
		return ncm;
	}

	@Override
	public void filter(){
		super.filter();
		Object r = Context.get(WebExecute.EXECUTE_RETURN);
		// 如果返回的是Page，自动重定向到当前主题下
		if(r instanceof Page){
			Page p = (Page)r;
			if(!p.getUri().startsWith("/")){
				p.setUri("/themes/" + theme + "/" + p.getUri());
			}
		}
	}

}
