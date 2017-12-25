package com.gigglehd.persistence;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigglehd.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,String>{
	@Query("from User u where u.username=:#{#user.username} and u.passwords=sha2(:#{#user.passwords},256)")
	public <U extends User> U findByUsernameAndPasswords(@Param("user") U user);
	
	@Modifying
	@Query("update User u set u.points=u.points+:#{#user.points} where u.username=:#{#user.username}")
	public void savePointsByUsername(@Param("user") User user);
	
	@Modifying
	@Query("update User u set u.lastlog=now() where u.username=:username")
	public void saveByUsername(@Param("username") String username);
	
	
	@Query("select u.lastlog from User u where u.username=:username")
	public Timestamp findLastlogByUsername(@Param("username") String username);
	
	public int countByUsername(String username);
	
	@Modifying
	@Query("update User u set u.sessionid=:#{#user.sessionid}, u.sessiontimeout=:#{#user.sessiontimeout} where u.username=:#{#user.username}")
	public void saveSessionidAndSessiontimeoutByUsername(@Param("user") User user);
	
	@Modifying
	@Query("update User u set u.lastlog=now() where u.username=:username")
	public void saveLastlogByUsername(@Param("username") String username);
	
	
	public <U extends User> U findUsernameAndSessionidAndSessintimeoutBySessionid(String sessionid);
	

	@Override
	@Query("insert into User u(u.username,u.passwords) values(u.username:#{#user.username} and u.passwords=sha2(#{#user.passwords},256))")
	default <S extends User> S save(@Param("user") S entity) {
		return null;
	}
	
/*//	@Query("select u from User u inner join RoleLvl r on u.points between r.min_points and r.max_points")
	@Query("select u from RoleLvl r inner join r.User u on u.points between r.min_points and r.max_points")
	public User findUsernameAndPointsAndLvl();
	*/
	
	
/*	public User getUser(User user);
	public void insert(User user);
	public void updatePoints(User user);
	public void updateLastLog(String username);
	public LocalDate getLastLog(String username);
	public List<User> getListByPoints();
	public int getId(String username);
	public User getSession(String sessionId);
	public void updateSession(User dto);*/
}
