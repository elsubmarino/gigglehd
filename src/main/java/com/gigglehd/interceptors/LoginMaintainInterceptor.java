package com.gigglehd.interceptors;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.gigglehd.domain.User;
import com.gigglehd.persistence.UserRepository;

@Component
public class LoginMaintainInterceptor implements HandlerInterceptor{
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie=WebUtils.getCookie(request, "id");
		if(cookie!=null) {
			String sessId=cookie.getValue();
			//User dto=userRepository.getSession(sessId);
			User dto=userRepository.findUsernameAndSessionidAndSessintimeoutBySessionid(sessId);
			LocalDateTime ldt=LocalDateTime.now();
			LocalDateTime ldt2=dto.getSessiontimeout().toLocalDateTime();
			if(!ldt2.isBefore(ldt)) {
				HttpSession sess=request.getSession();
				sess.setAttribute("user",dto);
			}
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
