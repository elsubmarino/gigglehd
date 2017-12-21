package com.gigglehd.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_subcategory")
public class SubCategory  {
	@Transient
	int num;
	int rootnum;
	String name;
	String content;
	@Id
	int sequence;

}
