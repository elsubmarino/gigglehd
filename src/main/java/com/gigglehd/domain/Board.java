package com.gigglehd.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="tbl_board")
public @Data class Board {
	@Transient
	int rownum;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	String writer;
	String title;
	String content;
	int hit;
	@Transient
	LocalDateTime datetime;
	LocalDate date;
	@UpdateTimestamp
	Timestamp updatedatetime;
	String subcategory;
	String maincategory;
	@Transient
	int replycount;
	@Transient
	int groupnum;
	int sequence;
	int lvl;
	
	
	
	@Transient
	String url;
	@Transient
	String menuName;
}
