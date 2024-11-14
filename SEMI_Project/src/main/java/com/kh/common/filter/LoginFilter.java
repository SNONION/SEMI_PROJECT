package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.vo.UserInfo;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/unionBoardListView.un","/boardDetailView.un","/deleteBoard.un","/popularBoardListView.un","/categoeyListView.un","*.sh"})
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
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
		
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpSession session = servletRequest.getSession();
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		
		if(loginUser != null) {			
			chain.doFilter(request, response);
		}
		else {
			session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스 입니다.");
			servletRequest.getRequestDispatcher("/views/common/loginPage.jsp").forward(servletRequest, servletResponse);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
