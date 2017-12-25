package com.gigglehd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Table(name="tbl_board_pictures")
@Entity
public class BoardPictures {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long num;
	@Column(name="root_num")
	@NotNull
	long rootNum;
	@NotNull
	String url;
	
}
