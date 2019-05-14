package com.comtop.topcloud.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StringUtils {

	public static String formatParamMap(Map<String, String> paraMap){
        StringBuffer sb = new StringBuffer();
        
	    List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(paraMap.entrySet());
	    // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
	    Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
	
	        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
	            return (o1.getKey()).toString().compareTo(o2.getKey());
	        }
	    });

	    for (Map.Entry<String, String> item : infoIds) {
            if (item.getKey() != null || item.getKey() != "") {
                String key = item.getKey();
                String val = item.getValue();
                if (!(val == "" || val == null)) {
                    sb.append(key + "=" + val + "&");
                }
            }
        }
        
        return sb.toString();
    }
	
}
