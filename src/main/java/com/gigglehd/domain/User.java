package com.gigglehd.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="user")
public @Data class User {
	@Id
	String username;
	String passwords;
	int points;
	@Transient
	int lvl;
	String sessionid;
	Timestamp sessiontimeout;
	@Transient
	String maintain;
}
