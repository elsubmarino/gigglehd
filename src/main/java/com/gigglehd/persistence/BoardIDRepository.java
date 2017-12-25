package com.gigglehd.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigglehd.domain.BoardIP;
@Repository
public interface BoardIDRepository extends CrudRepository<BoardIP,Integer>{
	@Query("select count(b) from BoardIP b where b.ip=:#{#boardIp.ip} and b.rootnum=:#{#boardIp.rootnum} and b.category=:#{#boardIp.category}")
	int countByIdAndNumAndCategory(@Param("boardIp") BoardIP boardIp);
}
