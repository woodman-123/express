package com.heima.express.common;

import java.util.List;

public class DataGridResult<T> {

	private List<T> rows;
	
	private Integer total;

	public DataGridResult() {
		super();
	}

	public DataGridResult(List<T> rows, Integer total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "DataGridResult [rows=" + rows + ", total=" + total + "]";
	}
	
	
	
	
}
