package com.gigglehd.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigglehd.domain.SubCategory;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory,Long>{
	@Query("from SubCategory s where s.rootNum=(select b.num from BigCategory b where b.name=:mainCategory)")
	public <S extends SubCategory> List<S> findByMainCategory(@Param("mainCategory") String mainCategory);
/*	public List<SubCategory> getList(String maincategory);
	public BigCategory getMainCategories(String name);
	public List<BigCategory> getListForBigCategory();
	public List<SubCategory> getSubcategoriesByBigcategory(String maincategory);*/
}
