package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.mem.model.MemberVO;

public class loginfilter implements Filter {
	
    public loginfilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		try {
			
			Object memNO = session.getAttribute("memNO");
			MemberVO test = new MemberVO();
			String test2 = (String) req.getAttribute("test");
			if(memNO == null) {
				session.setAttribute("location",req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/front-end/member/member/login.jsp");
				return;
			}else{
				session.setAttribute("memNO",memNO);
				chain.doFilter(request, response);	
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
