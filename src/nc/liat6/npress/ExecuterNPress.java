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
 * 网站执行器
 * 
 * @author 6tail
 * 
 */
public class ExecuterNPress extends WebExecute{

	/** 需要做缓存的方法列表 */
	private static final List<String> cacheMethods = new ArrayList<String>();

	static{
		cacheMethods.add("page");
		cacheMethods.add("detail");
	}

	@Override
	public void request(){
		super.request();

		// 如果调用的不是app包，不做缓存
		ClassMethod cm = Context.get("NPRESS_CLASS_METHOD");
		String klass = cm.getKlass();
		String method = cm.getMethod();
		if(!klass.startsWith("action.")){
			return;
		}
		Request r = Context.get(Statics.REQUEST);

		// 需要请求缓存
		if(cacheMethods.contains(method)){
			// 传来的id参数
			String id = r.get("id");
			// 传来的页码
			int pageNum = r.getPageNumber();

			//缓存文件唯一名称
			String fileName = klass + "-" + id + "-" + pageNum + ".html";

			File dir = new File(WebContext.REAL_PATH,Global.DEFAULT_CACHE_DIR);
			if(!dir.exists() || !dir.isDirectory()){
				dir.mkdirs();
			}
			File file = new File(dir,fileName);
			
			//如果缓存文件存在，就转到CacheAction显示缓存文件
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
			appendLog("页面赋值：\r\n");
		}
		while(it.hasNext()){
			String key = it.next();
			oreq.setAttribute(key,p.get(key));
			appendLog(Stringer.print("\t?=?",key,p.get(key)) + "\r\n");
		}
		appendLog(Stringer.print("返回页面：?",p.getUri()));
		writeLog();
		
		try{
			CacheResponse wrapperResponse = new CacheResponse(ores);
			oreq.getRequestDispatcher(p.getUri()).include(oreq,wrapperResponse);
			String html = wrapperResponse.getContent();
			ClassMethod cm = Context.get("NPRESS_CLASS_METHOD");
			String klass = cm.getKlass();
			String method = cm.getMethod();
			
			// 写缓存文件
			if(klass.startsWith("action.")){
				if(cacheMethods.contains(method)){
					// 传来的id参数
					String id = r.get("id");
					// 传来的页码
					int pageNum = r.getPageNumber();

					//缓存文件唯一名称
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
