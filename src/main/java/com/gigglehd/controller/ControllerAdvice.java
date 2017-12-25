package com.gigglehd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gigglehd.domain.BigCategory;
import com.gigglehd.domain.Reply;
import com.gigglehd.persistence.BigCategoryRepository;
import com.gigglehd.persistence.ReplyRepository;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	BigCategoryRepository bigCategoryRepository;
	
	@ModelAttribute("replyList")
	public List<Reply> test() {
		return replyRepository.getListBy30();
	}
	
	@ModelAttribute("menu")
	public List<BigCategory> menu(){
		return bigCategoryRepository.findByOrderByOrderingAscNumAsc();
	}
}
