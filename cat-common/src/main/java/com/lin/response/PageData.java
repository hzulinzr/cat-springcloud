package com.lin.response;

import lombok.Data;

import java.util.List;

/**
 * @author hzh 2019-09-04 14:30
 */
@Data
public class PageData<T> {

	private Integer totalCount;
	private List<T> data;

	public PageData(Integer totalCount, List<T> data) {
		this.totalCount = totalCount;
		this.data = data;
	}
}
