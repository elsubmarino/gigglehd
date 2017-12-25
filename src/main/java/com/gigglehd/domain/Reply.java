package com.gigglehd.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="tbl_reply")
public @Data class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long num;
	@NotNull
	String writer;
	@NotNull
	String content;
	@NotNull
	long root_num;
	long group_num;
	int lvl;
	int sequence;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	@Column(name="date",nullable=false)
	LocalDateTime date;
	
	public String getDate() {
		System.out.println(date+" datedate");
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");
		System.out.println(date.format(dtf)+" datedate2");
		return date.format(dtf).toString();
	}


	
}
