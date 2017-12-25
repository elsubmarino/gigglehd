package com.gigglehd.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_role_lvl")
public class RoleLvl {
	@Id
	int lvl;
	@NotNull
	int min_points;
	@NotNull
	int max_points;
}
