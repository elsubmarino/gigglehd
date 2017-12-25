package com.gigglehd.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gigglehd.domain.Reply;

@Repository
public interface ReplyRepository {
	public List<Reply> getList(long root_num);
	public List<Reply> getListBy30();
	public void insert(Reply dto);
	public void delete(long num);
	public void updateGroupNum(long num);
	public void updateSequence(Reply dto);
	public int getCount(long root_num);
}
