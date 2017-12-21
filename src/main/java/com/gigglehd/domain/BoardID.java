package com.gigglehd.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="tbl_board_id")
public class BoardID {
	String id;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	String category;

}
