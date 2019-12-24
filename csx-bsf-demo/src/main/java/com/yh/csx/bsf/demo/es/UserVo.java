package com.yh.csx.bsf.demo.es;

import com.yh.csx.bsf.elasticsearch.base.ElasticSearchAware;

public class UserVo implements ElasticSearchAware{

	private String id;
	private String name;

	public UserVo() {
		super();
	}

	public UserVo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + "]";
	}

}