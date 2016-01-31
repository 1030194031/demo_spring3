package com.os.controller.pay;

import com.os.comment.utils.alipay.CheckURL;
import com.os.comment.utils.alipay.Payment;
import com.os.comment.utils.alipay.SignatureHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/24 0024.
 */
@Controller
public class PayController {

    private static final String requestId = "1000000000000000000000000000"; // 订单支付订单号
    private static final String out_trade_no = "PAY0000";// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
    private static final String partner = "2088911375400259"; // 支付宝合作伙伴id  // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
    private static final String key = "ba1p6fl62sx4tujvgm391pfxtmh66unc"; // 支付宝安全校验码(账户内提取)
    private static final String seller_email = "511988575@qq.com"; // 卖家支付宝帐户
    //私钥
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALyIhuOXfmLD46xn38DR2snr6iAfIIJkEN1pqa+U+ZqzA743p5Cdh8WRU2/LFU3E8SZ4zJ5BMM+ab6uMju4QPr/cbFdCSsT1ps9tY/zKy4Yx8/qUtxQr837qL3+n91U0xCouHV/5+QoPMkjz8ZEiunSupHQcixy6lOqjDf+7YczPAgMBAAECgYEAgq9QcSpt3SBUDQnju9C7jAQPwjxRWJDsb83yZVcqveUgzxXc/Q11M6nM4E0xczr0veo2S+x8XSt8BLnlyXdRRpJMJqE2CbTcmPgc7bLR+rBDj549tb+yyombP7g3/2Xd2pJcOx4cOIt+JDyWe+kt3blm8hmhX9w4yNKYw56KSqECQQD3s5APYGK82bS4XhSEmQIh5wVrbyOvsM8flFw81kXz0r6rL39fkVCuxbIgve5csvSKKYy2m4lBY0q5PE3nTwilAkEAwtmArrU3FZQzOlfm4MlUjkp+5nBg2FxTcCNkaFuw+d0bn9VvNTi7vr5qRK5eoi3IGRfQ1MvXBTGBjcwRxHyRYwJBAOPb03bBpLKy1jMPoHJFypdjTDIDH+HblP0o3hy8w8bCo9EXtWU1aPINPIowDdhKave5tw64T2/OJgv2NHfG1V0CQFWccTDSCFjf275HvvaHHzLBwo4HVFXcAwEjW8uKdfDi2G9avNj6i2sciN6SapwBV4jG8Qd+ZIQwo0alIi1duhcCQG01FKM7d2T1GUuUIOGfmXTxi7dXPGwFGrBqstoJ07NSoezGyXw8xz/bQPtsndtUDNKLyP31buTFIV+r1zpaXeg=";

    /**
     * 跳转支付宝支付
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/pay/toAlipay")
    public String toAlipay(HttpServletRequest request) {
        try {
            return getAlipayUrl();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private String getAlipayUrl() throws Exception {
        String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
        String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
        String sign_type = "MD5";// 文件加密机制（不可以修改）
        String input_charset = "utf-8"; // （不可以修改）
        String body = "100-" + requestId + "-" + out_trade_no;// 商品描述，推荐格式：商品名称（订单编号：订单编号）
        String total_fee = "100";// 订单总价,差价尚须银行支付的金额
        // total_fee = String.valueOf(total_fee); // 订单总价
        String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）

        // subject 商品名称
        String subject = "orderno:" + requestId;
        // 扩展信息,存用户id和requestId.重要，必须存
        String extra_common_param = "100," + requestId;

        String show_url = "http://t.268xue.com/"; // 商品地址，
        // 根据集成的网站而定
        // 回调的地址
        String path = "http://t.268xue.com";
        String notify_url = path + "/order/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
        String return_url = path + "/order/alipaynotify/2"; // 支付完成后跳转返回的网址URL
        // 注意以上两个地址 要用 http://格式的完整路径
            /* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */
        String defaultbank = null;// request.getParameter("defaultbank");
        String paymethod = "directPay";
//            if (StringUtils.isNotEmpty(defaultbank)) {
//                paymethod = "bankPay";
//            } else {
        defaultbank = null;
        paymethod = "directPay";

        String submiturl = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod, defaultbank, extra_common_param);
        return "redirect:" + submiturl;
    }

    /**
     * 支付宝回调信息
     *
     * @param request
     * @param response
     * @param rtype
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/order/alipaynotify/{rtype}")
    public String alipayrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            // 获得POST 过来参数设置到新的params中
            Map<String, Object> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                /*
                 * 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
				 * 如果mysign和sign不相等也可以使用这段代码转化
				 */
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
                params.put(name, valueStr);
            }

            String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?" + "partner=" + partner + "&notify_id=" + request.getParameter("notify_id");
            String responseTxt = CheckURL.check(alipayNotifyURL);
            // 验证加密签名
            String mysign = SignatureHelper.sign(params, privateKey);
            // 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
            if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true")) {
                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
                // 订单编号,系统内的requestId
                String out_trade_no = request.getParameter("out_trade_no");

                String trade_no = request.getParameter("trade_no"); // 支付宝交易号
                // 总价
                String total_fee = request.getParameter("total_fee");
                // 订单名称
                // String get_subject = new
                // String(request.getParameter("subject").getBytes("ISO-8859-1"),
                // "UTF-8");
                // 订单说明
                String get_body = new String(request.getParameter("body"));
                get_body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
                String extra_common_param = request.getParameter("extra_common_param");// 扩展信息存用户的id
                // 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                if (request.getParameter("trade_status").equals("WAIT_BUYER_PAY")) {
                    // 等待买家付款
                    this.sendMessage(request, response, "fail");
                } else if (request.getParameter("trade_status").equals("TRADE_FINISHED") || request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
                    // 校验好状态,在这里可以写入订单的数据处理,

                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", total_fee);
                    sourceMap.put("out_trade_no", out_trade_no);
                    sourceMap.put("userId", extra_common_param.split(",")[0]);
                    sourceMap.put("requestId", extra_common_param.split(",")[1]);
//                    sourceMap.put("payType", PayType.ALIPAY.toString());
//                    Trxorder trxorder = trxorderService.getTrxorderByRequestId(extra_common_param.split(",")[1]);
//                    if (trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
//                        if (rtype == 1) {
//                            return null;
//                        } else {
//                            redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
//                            return "redirect:/front/success";
//                        }
//                    }
                    // 必须校验支付的金额
//                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
//                    if (ObjectUtils.isNotNull(res)) {
//
//                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
//
//                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
//                        if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
//                            sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
//                        } else {
//                            sendMessage(request, response, "fail");// 失败
//                        }
//                    } else {
//                        sendMessage(request, response, "fail");// 失败
//                    }

                }
            } else {
                redirectAttributes.addAttribute("", "请求异常");
                sendMessage(request, response, "fail");
            }
        } catch (Exception e) {
            try {
                this.sendMessage(request, response, "fail");
                return "error";
            } catch (IOException e1) {

            }
        }
        // 同步时跳转到成功页面
        if (rtype.longValue() == 2) {

            return "redirect:/front/success";
        }
        return null;
    }

    public void sendMessage(HttpServletRequest request, HttpServletResponse response, String content)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(content);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
