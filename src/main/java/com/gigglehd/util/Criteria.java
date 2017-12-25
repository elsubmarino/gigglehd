package com.gigglehd.util;

import lombok.Data;

public @Data class Criteria {
	int page = 1;
	int perPageNum = 10;
	String maincategory;
	String subcategory;
	String keywords;
	String searchType;


	public int getStartPage() {
		return (page - 1) * perPageNum;
	}

}
