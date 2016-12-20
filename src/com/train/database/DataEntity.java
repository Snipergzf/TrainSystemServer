package com.train.database;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
import com.train.config.Config;

/**
 * @author gzf
 *
 */
public class DataEntity {
	private HashMap<String, String> data;
	private String IPAddress;
	public DataEntity(String ipAddress) {
		this.IPAddress = ipAddress;
		data = new HashMap<>(Config.ParamCount);
	}
	public String toString() {
		if (data == null || data.isEmpty()) {
			return "";
		}
		StringBuilder sql = new StringBuilder();
		for (String key : data.keySet()) {
			//循环拼接SQL字符串，考虑到计算量大，这里对每个关键字不做检查
			sql.append(key);
			sql.append("=");
			sql.append(data.get(key));
			sql.append(",");
		}//循环结束
		
		sql.deleteCharAt(sql.length()-1);
		if (sql.length()>0) {
			sql.insert(0, "UPDATE "+Config.Table+" SET ");
			sql.append("WHERE ipaddress="+this.IPAddress);
		}
		return sql.toString();
	}
	public void update(String str){
		JSONObject updateJson;
		if (str != null && !str.equals("")) {
			updateJson = new JSONObject(str);
			Iterator keys = updateJson.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				data.put(key, updateJson.getString(key));
			}
		}
		updateJson = null;
	}
}
