package com.yh.csx.bsf.elasticsearch.base;

import com.yh.csx.bsf.core.base.BsfException;

public class ElasticSearchException extends BsfException {
	
	private static final long serialVersionUID = -6452468922321254517L;

	public ElasticSearchException(Exception exp) {
		super(exp);
	}

	public ElasticSearchException(String message) {
		super(message);
	}
}
