package com.vali.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vali.entity.Customer;


@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		if(user==null) {
			session.setAttribute("back-url", request.getRequestURI());
			response.sendRedirect("/account/login");
			return false;
		}
		if(!user.getAdmin()) {
			return false;
		}
		
		return true;
	}
}
