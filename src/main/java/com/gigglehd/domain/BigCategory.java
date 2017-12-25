package com.gigglehd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
	long num;
	@NotNull
	String name;
	@NotNull
	String content;
	@NotNull
	String superbase;
	@NotNull
	String link;
	@Null
	String details;
	@Column(name="order")
	@NotNull
	int ordering;



}
