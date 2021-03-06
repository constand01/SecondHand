package fileter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class adminlogin
 */
@WebFilter("/manager/*")
public class adminlogin implements Filter {

    /**
     * Default constructor. 
     */
    public adminlogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;       //重新获取request和reponse对象
		
		req.setCharacterEncoding("utf-8");                              //设置字符集
		resp.setContentType("text/html;charset=utf-8");
		
		
		
		HttpSession session=req.getSession();
		String flag=(String)session.getAttribute("isAdminLogin");     //获取session对象
		
		String request_url=req.getRequestURI();               //截取访问的url地址
		String ctxPath=req.getContextPath();
		String uri=request_url.substring(ctxPath.length());
		
		//if(uri.contains("login.jsp")||uri.contains("adminlogin"))
		
		if(uri.contains("admin_")) {                     //如果地址中包含后台的admin_标准 ，则判断用户是否为管理员
			if(flag!=null && flag.equals("1"))
			{chain.doFilter(req, resp);}
			else {
				PrintWriter out=resp.getWriter();
				out.write("<script>");
				out.write("alert('非管理员不能访问后端页面');");
				out.write("location.href='/SecondHand/login.jsp';");
				out.write("</script>");
				out.close();
				return;
			}
		}else {
			chain.doFilter(req, resp);
		}
		
		return;
		
		    // 不通过直接return
		// pass the request along the filter chain
		//chain.doFilter(req, resp);  //ͨ通过使用这条语句
	}
	
	
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
