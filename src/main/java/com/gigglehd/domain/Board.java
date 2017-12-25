package com.gigglehd.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="tbl_board")
public @Data class Board {
	@Transient
	long rownum;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long num;
	@NotNull
	String writer;
	@NotNull
	String title;
	@NotNull
	String content;
	long hit;
	@Transient
	LocalDateTime datetime;
	LocalDate date;
	@UpdateTimestamp
	Timestamp updatedatetime;
	@NotNull
	String subcategory;
	@NotNull
	String maincategory;
	@Transient
	long replycount;
	@Transient
	long group_num;
	int sequence;
	int lvl;
	
	
	
	
	@Transient
	String url;
	@Transient
	String menuName;
}
