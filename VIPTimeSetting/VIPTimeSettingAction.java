package com.sunyard.csr.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunyard.csr.common.StrUtil;
import com.sunyard.csr.common.DateUtil;
import com.sunyard.csr.common.GlobalKeyWord;
import com.sunyard.csr.common.JSONUtil;

public class VIPTimeSettingAction extends BaseAction{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response){
		super.execute(request, response);
		String returnStr = "";
		String type = request.getParameter("type");
		if("selectFault".equals(type)){
			returnStr = selectFault(request,response);
		}else if ("updateFault".equals(type)) {
			returnStr = updateFault(request,response);
		}else if ("deleteFault".equals(type)) {
			returnStr = deleteFault(request,response);
		}else if ("insertFault".equals(type)) {
			returnStr = insertFault(request,response);
		}
		return returnStr;
	}
	/*
	 * Ä¬ÈÏ²éÑ¯
	 */
	private String selectFault(HttpServletRequest request, HttpServletResponse response) {
		HashMap<?, ?> hs = null;
		String jsonStr = "";
		try{
			hs = StrUtil.getFormData(request);
			List<Object> list = this.csrBusinessService.queryListPageSqlInfo("tb_sso_pz_agentservtime_selectForConditions", hs);
			String count = (String)this.csrBusinessService.queryObjectSqlInfo("tb_sso_pz_agentservtime_selectForConditionsCount", hs);
			jsonStr = JSONUtil.listObjectToJSON(list, count);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	private String updateFault(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> hs = null;
		boolean flag = false;
		try{
			hs = StrUtil.getFormData(request);
			flag = this.csrBusinessService.updateSqlInfo("tb_sso_pz_agentservtime_update", hs);
		}catch (Exception e) {
			// TODO: handle exception
			flag = false;
			e.printStackTrace();
		}
		return flag ? GlobalKeyWord.RET_JSON_SUCCESS : GlobalKeyWord.RET_JSON_FAIL;
	}
	
	
	private String insertFault(HttpServletRequest request,HttpServletResponse response) {
		HashMap<String, String> hs = null;
		boolean flag = false;
		try {
			hs = StrUtil.getFormData(request);
		    flag = this.csrBusinessService.insertBoolSqlInfo(
						"tb_sso_pz_agentservtime_insert", hs);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		
		return flag ? GlobalKeyWord.RET_JSON_SUCCESS : GlobalKeyWord.RET_JSON_FAIL;
	}
	
	private String deleteFault(HttpServletRequest request,HttpServletResponse response) {
		HashMap<String, String> hs = null;
		boolean flag = false;
		try{
			hs = StrUtil.getFormData(request);
			//??
			List<Object> list = StrUtil.SplitObject((String)hs.get("ID"), ",", request);
			int delRows = this.getCsrBusinessService().batchDeleteIbatisSpring("tb_sso_pz_agentservtime_delete", list);
			if (delRows < 0) {
				flag = false;
			}else{
				flag = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			flag = false;
			e.printStackTrace();
		}
		return flag ? GlobalKeyWord.RET_JSON_SUCCESS : GlobalKeyWord.RET_JSON_FAIL;
	}

}
