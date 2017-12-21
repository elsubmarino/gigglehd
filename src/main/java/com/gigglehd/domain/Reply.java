package com.gigglehd.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tbl_reply")
public @Data class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	String writer;
	String content;
	int root_num;
	int group_num;
	int lvl;
	int sequence;
	LocalDateTime date;
	
	public String getDate() {
		System.out.println(date+" datedate");
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");
		System.out.println(date.format(dtf)+" datedate2");
		return date.format(dtf).toString();
	}


	
}
