package com.gigglehd.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gigglehd.domain.Board;
import com.gigglehd.util.Criteria;

@Repository
public interface BoardRepository {
	public List<Board> getList(Criteria cri);
	public Board getOne(long num);
	public int getCount(Criteria cri);
	public int getCountForUltimateSearch(Criteria cri);
	public void insert(Board dto);
	public void modify(Board dto);
	public void delete(long num);
	public void updateHit(long num);
	public int updateDelete(long num);
	public int updateRecommendation(long num);
	public int getUpdateDeleteCount(long num);
	public int getUpdateRecommendationCount(long num);
	public List<Board> getUltimateSearch(Criteria cri);
	public List<Board> getListByPopularity(Map<String,Object> map);
	public void updateGroupNum(Board dto);
	public void updateSequence(Board dto);
}
