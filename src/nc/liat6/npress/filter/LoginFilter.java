package nc.liat6.npress.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nc.liat6.npress.Global;
import nc.liat6.npress.bean.User;


/**
 * 登录过滤
 * @author 6tail
 *
 */
public class LoginFilter implements Filter{

	public void init(FilterConfig fc) throws ServletException{

	}
	
	private void logout(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		// 获取AJAX请求标识
		String headAjax = req.getHeader("x-requested-with");
		if(null == headAjax){
			if("NlfHttpRequest".equals(req.getHeader("nlf-requested-with"))){
				req.getRequestDispatcher("/sessionout.jsp").forward(req,res);
			}else{
				req.getRequestDispatcher("/goout.jsp").forward(req,res);
			}
			return;
		}else{
			String jsonStr = "{'data':null,'msg':'会话超时，请重新登录！','success':false}";
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonStr);
			res.getWriter().flush();
		}
	}
	
	private void limit(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		// 获取AJAX请求标识
		String headAjax = req.getHeader("x-requested-with");
		if(null == headAjax){
			if("NlfHttpRequest".equals(req.getHeader("nlf-requested-with"))){
				req.getRequestDispatcher("/limit.jsp").forward(req,res);
			}else{
				req.getRequestDispatcher("/goout.jsp").forward(req,res);
			}
			return;
		}else{
			String jsonStr = "{'data':null,'msg':'会话超时，请重新登录！','success':false}";
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonStr);
			res.getWriter().flush();
		}
	}

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		// 访问地址
		String path = req.getServletPath();

		if(path.startsWith("/admin-") || path.startsWith("/admin/")){
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute(Global.SESSION_USER);
			if(null == user){
				logout(req,res);
			}else{
				if(1!=user.getAdmin()){
					limit(req,res);
				}else{
					chain.doFilter(request,response);
				}
			}
		}else{
			chain.doFilter(request,response);
		}
	}

	public void destroy(){}

}