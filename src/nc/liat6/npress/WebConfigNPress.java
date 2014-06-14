package nc.liat6.npress;

import java.util.List;
import java.util.Map;

import nc.liat6.frame.Factory;
import nc.liat6.frame.execute.IExecute;
import nc.liat6.frame.web.config.IWebManager;
import nc.liat6.frame.web.config.WebConfig;
import nc.liat6.npress.init.IInit;

/**
 * ��վ����
 * @author 6tail
 *
 */
public class WebConfigNPress extends WebConfig{

	@Override
	public void init(){
		super.init();
	}
	
	@Override
	public String getErrorPage(){
			return "/themes/"+getGlobalVars().get("theme")+"/error.jsp";
	}
	
	@Override
	public Map<String,Object> getGlobalVars(){
		Map<String,Object> m = super.getGlobalVars();
		//������վ����
		m.put("WEB_AUTHOR",Global.DEFAULT_WEB_AUTHOR);
		//������վ��
		m.put("WEB_NAME",Global.DEFAULT_WEB_NAME);
		//������վ�ؼ���
		m.put("WEB_KEY",Global.DEFAULT_WEB_KEY);
		//������վ����
		m.put("WEB_DESC",Global.DEFAULT_WEB_DESCIPTION);
		//������վ��Ȩ
		m.put("WEB_COPYRIGHT",Global.DEFAULT_WEB_COPYRIGHT);
		//������վ����
		m.put("theme",Global.DEFAULT_THEME);
		return m;
	}
	
	@Override
	public List<String> getForbiddenPaths(){
		List<String> l = super.getForbiddenPaths();
		//���η���
		l.add("/nc.liat6.npress");
		return l;
	}
	
	@Override
	public IWebManager getWebManager(){
		return new WebManagerNPress(this);
	}
	
	@Override
	public IExecute getExecuter(){
		return new ExecuterNPress();
	}
	
	@Override
	public void start(){
		super.start();
		
		//��ʼ��
		List<String> inits = Factory.getImpls(IInit.class.getName());
		for(String klass:inits){
			IInit initer = Factory.getCaller().newInstance(klass);
			initer.init();
		}
	}
}
