package com.gigglehd.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="tbl_board_pictures")
public class BoardPictures {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	int rootnum;
	String url;
	
}
