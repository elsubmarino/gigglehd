package com.gigglehd.persistence;

import com.gigglehd.domain.BoardID;

public interface BoardIDMapper {
	public void insert(BoardID dto);
	public int getOne(BoardID dto);
}
