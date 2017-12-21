package com.gigglehd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_bigcategory")
public class BigCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	String name;
	String content;
	String superbase;
	String link;
	String details;



}
