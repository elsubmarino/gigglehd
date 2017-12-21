package com.gigglehd.persistence;

import java.util.List;
import java.util.Map;

import com.gigglehd.domain.Board;
import com.gigglehd.util.Criteria;

public interface BoardMapper {
	public List<Board> getList(Criteria cri);
	public Board getOne(int num);
	public int getCount(Criteria cri);
	public int getCountForUltimateSearch(Criteria cri);
	public void insert(Board dto);
	public void modify(Board dto);
	public void delete(int num);
	public void updateHit(int num);
	public int updateDelete(int num);
	public int updateRecommendation(int num);
	public int getUpdateDeleteCount(int num);
	public int getUpdateRecommendationCount(int num);
	public List<Board> getUltimateSearch(Criteria cri);
	public List<Board> getListByPopularity(Map<String,Object> map);
	public void updateGroupNum(Board dto);
	public void updateSequence(Board dto);
}
