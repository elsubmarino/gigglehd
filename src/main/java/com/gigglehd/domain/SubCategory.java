package com.gigglehd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_subcategory")
public class SubCategory  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long num;
	@Transient
	long rootnum;
	@NotNull
	String name;
	@NotNull
	String content;
	
	@Column(name="root_num")
	@NotNull
	long rootNum;
	
}
