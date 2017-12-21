package com.gigglehd.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.gigglehd.domain.User;
import com.gigglehd.persistence.UserMapper;

@RestController
public class LoginController {
	@Autowired
	UserMapper userMapper;

	@PostMapping("/login")
	@Transactional
	public @ResponseBody Map<String, Object> login(User dto, HttpSession session,
			HttpServletResponse response) {
		System.out.println("there?");
		Map<String, Object> map = new HashMap<>();
		if (dto.getMaintain() != null && dto.getMaintain().equals("on")) {
			Cookie cookie = new Cookie("id", session.getId());
			dto.setSessionid(session.getId());
			LocalDateTime ldt = LocalDateTime.now();
			ldt = ldt.plus(7, ChronoUnit.DAYS);
			dto.setSessiontimeout(Timestamp.valueOf(ldt));
			userMapper.updateSession(dto);
			response.addCookie(cookie);
		}
		User user = userMapper.getUser(dto);
		if (user != null) {
			session.setAttribute("user", user);
			dto.setPoints(1);
			LocalDate when = userMapper.getLastLog(dto.getUsername());
			LocalDate date = LocalDate.now();
			if (when.isBefore(date)) {
				userMapper.updatePoints(dto);
			}
			userMapper.updateLastLog(dto.getUsername());
			map.put("status", "success");
		} else {
			map.put("status", "fail");
		}
		return map;

	}

	@RequestMapping("/logout")
	public void logout(HttpSession sess, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie id = WebUtils.getCookie(request, "id");
		if (id != null) {
			id.setMaxAge(0);
			response.addCookie(id);
		}
		sess.invalidate();
		response.sendRedirect("/");

	}

	@PostMapping("/signup")
	public void signupPOST(User dto, HttpServletResponse response) throws IOException {
		userMapper.insert(dto);
		response.sendRedirect("/");
	}

}
