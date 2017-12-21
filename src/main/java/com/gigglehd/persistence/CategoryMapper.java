package com.gigglehd.persistence;

import java.util.List;

import com.gigglehd.domain.BigCategory;
import com.gigglehd.domain.SubCategory;

public interface CategoryMapper {
	public List<SubCategory> getList(String mainCategory);
	public BigCategory getMainCategories(String name);
	public List<BigCategory> getListForBigCategory();
	public List<SubCategory> getSubcategoriesByBigcategory(String mainCategory);
}
