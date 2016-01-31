package com.os.comment.utils.alipay;

import java.util.*;

/**
 * Created by Administrator on 2016/1/24 0024.
 */
public class SignatureHelper {
    public static String sign(Map params, String privateKey) {
        Properties properties = new Properties();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            Object value = params.get(name);
            if ((name != null) && (!name.equalsIgnoreCase("sign")) && (!name.equalsIgnoreCase("sign_type"))) {
                properties.setProperty(name, value.toString());
            }
        }
        String content = getSignatureContent(properties);
        return sign(content, privateKey);
    }

    public static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        List keys = new ArrayList(properties.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = properties.getProperty(key);
            content.append(String.valueOf(i != 0 ? "&" : "") + key + "=" + value);
        }

        return content.toString();
    }

    public static String sign(String content, String privateKey) {
        if (privateKey == null)
            return null;
        String signBefore = String.valueOf(content) + privateKey;

        return Md5Encrypt.md5(signBefore);
    }
}
