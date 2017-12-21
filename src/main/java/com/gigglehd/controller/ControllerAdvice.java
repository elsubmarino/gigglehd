package com.gigglehd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gigglehd.domain.BigCategory;
import com.gigglehd.domain.Reply;
import com.gigglehd.persistence.CategoryMapper;
import com.gigglehd.persistence.ReplyMapper;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	@Autowired
	ReplyMapper replyMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@ModelAttribute("replyList")
	public List<Reply> test() {
		return replyMapper.getListBy30();
	}
	
	@ModelAttribute("menu")
	public List<BigCategory> menu(){
		return categoryMapper.getListForBigCategory();
	}
}
