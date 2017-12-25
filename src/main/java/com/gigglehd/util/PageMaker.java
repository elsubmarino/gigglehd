package com.gigglehd.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

public @Data class PageMaker {
	Criteria cri;
	int displayPageNum=10;
	int startPage;
	int endPage;
	boolean prev;
	boolean next;
	int totalCount;
	
	public void calc() {
		endPage=(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage=(endPage-displayPageNum)+1;
		int tempEndPage=(int)(Math.ceil(totalCount/(double)displayPageNum));
		if(endPage>tempEndPage)
			endPage=tempEndPage;
		prev=startPage==1?false:true;
		next=cri.getPerPageNum()*endPage>=totalCount?false:true;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount=totalCount;
		calc();
	}
	
	public String makeQuery(int page) {
		UriComponents uri=UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("keywords",cri.getKeywords()).queryParam("searchType", cri.getSearchType()).queryParam("subcategory", cri.getSubcategory()).queryParam("maincategory", cri.getMaincategory()).build();
		return uri.toString();
	}
	
	
	
}
