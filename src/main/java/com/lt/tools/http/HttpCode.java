package com.lt.tools.http;

 

/**
 * Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 * 

 */
public class HttpCode {
	/**
	 * 200请求成功
	 */
	public static final int OK = 200;
	/**
	 * 207频繁操作
	 */
	public static final int MULTI_STATUS = 207;

	/**
	 * 303登录失败
	 */
	public static final int LOGIN_FAIL = 303;

	/**
	 * 400请求参数出错
	 */
	public static final int BAD_REQUEST = 400;

	/**
	 * 401没有登录
	 */
	public static final int UNAUTHORIZED = 401;

	/**
	 * 403没有权限
	 */
	public static final int FORBIDDEN = 403;

	/**
	 * 404找不到页面
	 */
	public static final int NOT_FOUND = 404;

	/**
	 * 408请求超时
	 */
	public static final int REQUEST_TIMEOUT  = 408;

	/**
	 * 409发生冲突
	 */
	public static final int CONFLICT = 409;

	/**
	 * 410已被删除
	 */
	public static final int GONE = 410;

	/**
	 * 423已被锁定
	 */
	public static final int LOCKED = 423;

	/**
	 * 500服务器出错
	 */
	public static final int INTERNAL_SERVER_ERROR = 500;

}
