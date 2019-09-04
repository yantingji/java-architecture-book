package com.itedu.entity;

import java.io.Serializable;

public class RankFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String rank;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
