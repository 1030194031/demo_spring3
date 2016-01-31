package com.os.comment.utils.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 支付宝工具类
 * Created by Administrator on 2016/1/24 0024.
 */
public class Payment {
    /**
     *
     * @param paygateway 支付接口（不可以修改） https://mapi.alipay.com/gateway.do?
     * @param service 快速付款交易服务（不可以修改） create_direct_pay_by_user
     * @param sign_type 文件加密机制（不可以修改） MD5
     * @param out_trade_no 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
     * @param input_charset （不可以修改） utf-8
     * @param partner 支付宝合作伙伴id
     * @param key 安全校验码（key）  支付宝提供的key
     * @param show_url 展示地址
     * @param body 参数名 商品描述：	3989-398920160124171414325-PAY2127
     * @param total_fee 总价
     * @param payment_type 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
     * @param seller_email 卖家邮箱
     * @param subject 支付宝订单显示商品名称 商品名称：	orderno:398920160124171414325
     * @param notify_url  接口异步通知url 支付宝服务器主动通知商户服务器里指定的页面http路径。
     * @param return_url 页面跳转同步通知页面路径 支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径
     * @param paymethod 默认支付方式。只有三个值可选择填写：bankPay(网银);cartoon(卡通); directPay(余额)
     * @param defaultbank defaultbank：默认网银。当paymethod选择是网银时有效
     * @param extra_common_param 扩展信息,存用户id和requestId.重要，必须存
     * @return
     */
    public static String CreateUrl(String paygateway, String service, String sign_type, String out_trade_no, String input_charset, String partner, String key, String show_url, String body, String total_fee, String payment_type, String seller_email, String subject, String notify_url, String return_url, String paymethod, String defaultbank, String extra_common_param) {
        Map params = new HashMap();
        params.put("service", service);
        params.put("partner", partner);
        params.put("subject", subject);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", total_fee);
        params.put("show_url", show_url);
        params.put("payment_type", payment_type);
        params.put("seller_email", seller_email);
        params.put("return_url", return_url);
        params.put("notify_url", notify_url);
        params.put("paymethod", paymethod);
        params.put("extra_common_param", extra_common_param);
        if ((defaultbank != null) && (!"".equals(defaultbank))) {
            params.put("defaultbank", defaultbank);
        }
        params.put("_input_charset", input_charset);
        String prestr = "";
        prestr = String.valueOf(prestr) + key;
        String sign = Md5Encrypt.md5(getContent(params, key));
        String parameter = "";
        parameter = String.valueOf(parameter) + paygateway;
        List keys = new ArrayList(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            try {
                parameter = String.valueOf(parameter) + keys.get(i) + "=" + URLEncoder.encode((String) params.get(keys.get(i)), input_charset) + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        parameter = String.valueOf(parameter) + "sign=" + sign + "&sign_type=" + sign_type;
        return parameter;
    }

    private static String getContent(Map params, String privateKey) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (i == keys.size() - 1)
                prestr = String.valueOf(prestr) + key + "=" + value;
            else {
                prestr = String.valueOf(prestr) + key + "=" + value + "&";
            }
        }
        return String.valueOf(prestr) + privateKey;
    }
}
