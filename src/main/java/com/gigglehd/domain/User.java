package com.gigglehd.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "tbl_user")
public @Data class User {
	@Id
	String username;
	@NotNull
	String passwords;

	long points;
	@Transient
	int lvl;
	String sessionid;
	Timestamp sessiontimeout;
	@Transient
	String maintain;
	@NotNull
	Timestamp lastlog;
	
}
