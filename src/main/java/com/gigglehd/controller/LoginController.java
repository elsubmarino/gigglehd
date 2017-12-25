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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.gigglehd.domain.User;
import com.gigglehd.persistence.UserRepository;

@RestController
public class LoginController {
	@Autowired
	UserRepository userRepository;

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
//			userRepository.updateSession(dto);
			userRepository.saveSessionidAndSessiontimeoutByUsername(dto);
			response.addCookie(cookie);
		}
		//User user = userRepository.getUser(dto);
		User user = userRepository.findByUsernameAndPasswords(dto);
		if (user != null) {
			session.setAttribute("user", user);
			dto.setPoints(1);
			//LocalDate when = userRepository.getLastLog(dto.getUsername());
			LocalDate when = userRepository.findLastlogByUsername(dto.getUsername()).toLocalDateTime().toLocalDate();
			LocalDate date = LocalDate.now();
			if (when.isBefore(date)) {
				//userRepository.updatePoints(dto);
				userRepository.savePointsByUsername(dto);
			}
			//userRepository.updateLastLog(dto.getUsername());
			userRepository.saveLastlogByUsername(dto.getUsername());
			map.put("status", "success");
		} else {
			map.put("status", "fail");
		}
		return map;

	}

	@RequestMapping("/logout")
	public void logout(HttpSession sess, HttpServletRequest request, HttpServletResponse response,@CookieValue(name="id",required=false) Cookie id) throws IOException {
		if (id != null) {
			id.setMaxAge(0);
			response.addCookie(id);
		}
		sess.invalidate();
		response.sendRedirect("/");

	}

	@PostMapping("/signup")
	public void signupPOST(User dto, HttpServletResponse response) throws IOException {
		///userRepository.insert(dto);
		userRepository.save(dto);
		response.sendRedirect("/");
	}

}
