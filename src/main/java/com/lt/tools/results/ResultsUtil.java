package com.lt.tools.results;

/**
 * 业务返回结果辅助类
 * @author 刘涛
 *
 */
public class ResultsUtil {
	
	/**
	 * 得到一个状态码为成功的结果
	 * @return
	 */
	public static Results getSuccessResult(){
		return (new Results()).setCode(ResultsConstants.RESULTS_CODE_SUCCESS);
	}
	
	/**
	 * 得到一个状态码为失败的结果
	 * @return
	 */
	public static Results getErrorResult(String msg){
		return (new Results()).setCode(ResultsConstants.RESULTS_CODE_ERROR).setMsg(msg);
	}

}
