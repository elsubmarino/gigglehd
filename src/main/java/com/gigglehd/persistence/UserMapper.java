package com.gigglehd.persistence;

import java.time.LocalDate;
import java.util.List;

import com.gigglehd.domain.User;

public interface UserMapper {
	public User getUser(User user);
	public void insert(User user);
	public void updatePoints(User user);
	public void updateLastLog(String username);
	public LocalDate getLastLog(String username);
	public List<User> getListByPoints();
	public int getId(String username);
	public User getSession(String sessionId);
	public void updateSession(User dto);
}
