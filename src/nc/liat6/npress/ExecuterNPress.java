package nc.liat6.npress;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.liat6.frame.context.Context;
import nc.liat6.frame.context.Statics;
import nc.liat6.frame.exception.BadException;
import nc.liat6.frame.execute.Request;
import nc.liat6.frame.execute.Response;
import nc.liat6.frame.util.Stringer;
import nc.liat6.frame.web.WebContext;
import nc.liat6.frame.web.WebExecute;
import nc.liat6.frame.web.config.ClassMethod;
import nc.liat6.frame.web.response.Page;
import nc.liat6.npress.cache.CacheAction;
import nc.liat6.npress.cache.CacheResponse;

/**
 * ��վִ����
 * 
 * @author 6tail
 * 
 */
public class ExecuterNPress extends WebExecute{

	/** ��Ҫ������ķ����б� */
	private static final List<String> cacheMethods = new ArrayList<String>();

	static{
		cacheMethods.add("page");
		cacheMethods.add("detail");
	}

	@Override
	public void request(){
		super.request();

		// ������õĲ���app������������
		ClassMethod cm = Context.get("NPRESS_CLASS_METHOD");
		String klass = cm.getKlass();
		String method = cm.getMethod();
		if(!klass.startsWith("action.")){
			return;
		}
		Request r = Context.get(Statics.REQUEST);

		// ��Ҫ���󻺴�
		if(cacheMethods.contains(method)){
			// ������id����
			String id = r.get("id");
			// ������ҳ��
			int pageNum = r.getPageNumber();

			//�����ļ�Ψһ����
			String fileName = klass + "-" + id + "-" + pageNum + ".html";

			File dir = new File(WebContext.REAL_PATH,Global.DEFAULT_CACHE_DIR);
			if(!dir.exists() || !dir.isDirectory()){
				dir.mkdirs();
			}
			File file = new File(dir,fileName);
			
			//��������ļ����ڣ���ת��CacheAction��ʾ�����ļ�
			if(file.exists()){
				cm.setKlass(CacheAction.class.getName());
				cm.setMethod("getFile");
				r.setParam("file",fileName);
			}
		}
	}

	protected void responsePage(Page p){
		Request r = Context.get(Statics.REQUEST);
		Response res = Context.get(Statics.RESPONSE);
		HttpServletRequest oreq = r.find(TAG_REQUEST);
		HttpServletResponse ores = res.find(TAG_RESPONSE);
		
		//debug
		Iterator<String> it = p.keySet().iterator();
		if(it.hasNext()){
			appendLog("ҳ�渳ֵ��\r\n");
		}
		while(it.hasNext()){
			String key = it.next();
			oreq.setAttribute(key,p.get(key));
			appendLog(Stringer.print("\t?=?",key,p.get(key)) + "\r\n");
		}
		appendLog(Stringer.print("����ҳ�棺?",p.getUri()));
		writeLog();
		
		try{
			CacheResponse wrapperResponse = new CacheResponse(ores);
			oreq.getRequestDispatcher(p.getUri()).include(oreq,wrapperResponse);
			String html = wrapperResponse.getContent();
			ClassMethod cm = Context.get("NPRESS_CLASS_METHOD");
			String klass = cm.getKlass();
			String method = cm.getMethod();
			
			// д�����ļ�
			if(klass.startsWith("action.")){
				if(cacheMethods.contains(method)){
					// ������id����
					String id = r.get("id");
					// ������ҳ��
					int pageNum = r.getPageNumber();

					//�����ļ�Ψһ����
					String fileName = klass + "-" + id + "-" + pageNum + ".html";

					File dir = new File(WebContext.REAL_PATH,Global.DEFAULT_CACHE_DIR);
					if(!dir.exists() || !dir.isDirectory()){
						dir.mkdirs();
					}
					File file = new File(dir,fileName);
					Stringer.writeToFile(html,file,"UTF-8");
				}
			}
			ores.setContentType("text/html");
			ores.getOutputStream().write(html.getBytes("UTF-8"));
			return;
		}catch(Exception e){
			throw new BadException(e);
		}
	}

}
