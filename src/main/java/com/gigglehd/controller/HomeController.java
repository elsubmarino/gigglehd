package com.gigglehd.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gigglehd.persistence.BoardMapper;
import com.gigglehd.persistence.CategoryMapper;
import com.gigglehd.persistence.UserMapper;
import com.gigglehd.util.Criteria;

@Controller
public class HomeController {
	@Autowired
	ServletContext servletContext;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	BoardMapper boardMapper;

	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		model.addAttribute("locale", currentLocale);
		return "/main/home";
	}

	@RequestMapping("/offline")
	public String offline() {
		return "/offline";
	}

	@RequestMapping("/points")
	public String points(Model model) {
		model.addAttribute("ranking", userMapper.getListByPoints());
		model.addAttribute("mainCategory", categoryMapper.getMainCategories(null));

		return "/main/points";
	}

	@RequestMapping("/popular")
	public String popular(Model model) {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("mainCategory", "포럼");
		LocalDateTime ld = LocalDateTime.now();
		hashMap.put("date", Timestamp.valueOf(ld.plus(-7, ChronoUnit.DAYS)));
		hashMap.put("perPageNum", 20);
		model.addAttribute("forumsWeekly", boardMapper.getListByPopularity(hashMap));
		hashMap.put("date", Timestamp.valueOf(ld.plus(-30, ChronoUnit.DAYS)));
		model.addAttribute("forumsMonthly", boardMapper.getListByPopularity(hashMap));
		hashMap.put("mainCategory", "커뮤니티");
		hashMap.put("date", Timestamp.valueOf(ld.plus(-7, ChronoUnit.DAYS)));
		hashMap.put("perPageNum", 10);
		model.addAttribute("communityWeekly", boardMapper.getListByPopularity(hashMap));
		hashMap.put("date", Timestamp.valueOf(ld.plus(-30, ChronoUnit.DAYS)));
		model.addAttribute("comunityMonthly", boardMapper.getListByPopularity(hashMap));

		return "/main/popular";
	}

	@RequestMapping("/signup")
	public String signup(HttpServletResponse response) {
		return "/main/signup";

	}

}
