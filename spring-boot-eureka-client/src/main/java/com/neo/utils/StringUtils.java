package com.neo.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StringUtils {

    @SuppressWarnings("rawtypes")
	public static String formatParamMap(Map<String, String> paraMap){
        StringBuffer sb = new StringBuffer();
        Set<?> es = paraMap.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        
        return sb.toString();
    }
}
