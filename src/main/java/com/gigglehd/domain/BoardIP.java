package com.gigglehd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Table(name="tbl_board_ip")
@Entity
public class BoardIP {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int num;
	@NotNull
	String ip;
	@NotNull
	long rootnum;
	@NotNull
	String category;

}
