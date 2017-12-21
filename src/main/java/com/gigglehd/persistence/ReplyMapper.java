package com.gigglehd.persistence;

import java.util.List;

import com.gigglehd.domain.Reply;

public interface ReplyMapper {
	public List<Reply> getList(int root_num);
	public List<Reply> getListBy30();
	public void insert(Reply dto);
	public void delete(int num);
	public void updateGroupNum(int num);
	public void updateSequence(Reply dto);
	public int getCount(int root_num);
}
