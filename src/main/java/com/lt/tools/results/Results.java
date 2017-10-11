package com.lt.tools.results;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Results implements Serializable{

	/**
	 * 状态
	 */
	public int code;
	/**
	 * 内容
	 */
	public Object data;
	/**
	 * 提示消息
	 */
	public String msg;
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 页数
	 */
	private int pageSize;
	
	

	public long getTotal() {
		return total;
	}

	public Results setTotal(long total) {
		this.total = total;
		return this;
	}

	public int getPage() {
		return page;
	}

	public Results setPage(int page) {
		this.page = page;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Results setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public int getCode() {
		return code;
	}

	public Results setCode(int code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Results setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Results setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	

}
