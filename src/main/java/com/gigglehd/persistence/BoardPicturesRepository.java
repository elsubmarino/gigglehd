package com.gigglehd.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gigglehd.domain.BoardPictures;

@Repository
public interface BoardPicturesRepository extends CrudRepository<BoardPictures,Long>{
	public <B extends BoardPictures> List<B> findByRootNum(long root_num);
	//public List<BoardPicturesRepository> getListByRootNum(long root_num);
	//public void insert(BoardPictures dto);
	
}
