package com.gigglehd.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gigglehd.domain.BigCategory;

@Repository
public interface BigCategoryRepository extends CrudRepository<BigCategory,Long>{
	public BigCategory findByName(String name);
	//@Query("from BigCategory b order by b.ordering asc, b.num asc")
	public <B extends BigCategory> List<B> findByOrderByOrderingAscNumAsc();
	//public List<BigCategory> findBigCategoryOrderByOrderingAsc();
/*	public List<SubCategory> getList(String maincategory);
	public BigCategory getMainCategories(String name);
	public List<BigCategory> getListForBigCategory();
	public List<SubCategory> getSubcategoriesByBigcategory(String maincategory);*/
}
