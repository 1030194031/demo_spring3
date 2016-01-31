package com.os.comment.utils;


import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * isEmpty 字符串为空
     * isNotEmpty 字符串非空
     * isNumber 判断是否是数字
     * validEmail 邮箱验证
     * handleEmail 邮箱屏蔽处理
     * isMobileNo 手机号判断
     * starMobile 手机号码屏蔽处理
     * phoneHandler 手机号码屏蔽处理处理
     * validMaxLen 判断字符串最大长度
     * validMinLen 判断字符串最小长度
     * equals 判断字符串是否相同
     * toInt 字符串转数字
     * arrayToString 数组转成String
     * getWebInfPath 获取文件的绝对路径
     * getRootPath 同上吧  暂时不知道有啥区别
     * getFileSize 获取输入大小的占用内容大小
     * toUTF8 转换utf-8
     * toCharacter 转换指定的字符集
     * replaceEnter 去html
     * getTxtWithoutHTMLElement 获取html中的内容（去除html标签）
     * toTrim 去除空格
     * cutffStr 截取添加追加字符
     * getLength 截串，超过长度 省略
     * getRandStr 随机生成数字
     * getSysTimeRandom 根据系统时间生成随机码
     * getRandomString 随机生成字母和数字的码
     * createRandom  创建指定数量的随机字符串
     * convertToTimestamp 时间转为时间戳
     * fromtPercent 百分比格式化数字
     * fromtCurrency 货币
     */

    /**
     * 字符串为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    /**
     * 字符串非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if(str != null && !str.trim().equals("")) {
            boolean flag = false;

            try {
                Long.parseLong(str);
                flag = true;
            } catch (Exception var3) {
                flag = false;
            }

            return flag;
        } else {
            return false;
        }
    }

    /**
     * 邮箱验证
     * @param sEmail
     * @return
     */
    public static boolean validEmail(String sEmail) {
        String pattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return sEmail.matches(pattern);
    }

    /**
     * 邮箱屏蔽处理
     * @param email
     * @return
     */
    public static String handleEmail(String email) {
        if(email == null) {
            return "";
        } else {
            String[] aryEmail = email.split("@");
            if(aryEmail != null && aryEmail.length == 2 && aryEmail[0] != null) {
                String firstPart = aryEmail[0].substring(aryEmail[0].length() / 2, aryEmail[0].length());
                if(firstPart != null && !"".equals(firstPart)) {
                    char[] repeatChar = new char[firstPart.length()];

                    for(int i = 0; i < firstPart.length(); ++i) {
                        repeatChar[i] = 42;
                    }

                    email = email.replaceFirst(firstPart + "@", new String(repeatChar) + "@");
                }
            }

            return email;
        }
    }

    /**
     * 手机号判断
     * @param tocheckNo
     * @return
     */
    public static boolean isMobileNo(String tocheckNo) {
        return Pattern.matches("^1\\d{10}$", tocheckNo);
    }

    /**
     * 手机号码屏蔽处理
     * @param mobile
     * @return
     */
    public static String starMobile(String mobile) {
        if(mobile.length() == 11) {
            String starmobile = mobile.charAt(0) + String.valueOf(mobile.charAt(1)) + mobile.charAt(2) + "****" + mobile.charAt(7) + mobile.charAt(8) + mobile.charAt(9) + mobile.charAt(10);
            return starmobile;
        } else {
            return mobile;
        }
    }

    /**
     * 手机号码屏蔽处理处理
     * @param phone
     * @return
     */
    public static String phoneHandler(String phone){
        phone=phone.substring(0,3)+"****"+phone.substring(phone.length()-4,phone.length());
        return phone;
    }

    /**
     * 判断字符串最大长度
     * @param str
     * @param length
     * @return
     */
    public static boolean validMaxLen(String str, int length) {
        return str != null && !str.equals("")?str.length() <= length:false;
    }

    /**
     * 判断字符串最小长度
     * @param str
     * @param length
     * @return
     */
    public static boolean validMinLen(String str, int length) {
        return str != null && !str.equals("")?str.length() >= length:false;
    }

    /**
     * 判断字符串是否相同
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 != null && !str1.equals("") && str2 != null && !str2.equals("")?str1.equals(str2):false;
    }

    /**
     * 字符串转数字
     * @param str
     * @return
     */
    public static int toInt(String str) {
        if(str != null && !str.equals("")) {
            int value1;
            try {
                value1 = Integer.parseInt(str);
            } catch (Exception var3) {
                var3.printStackTrace();
                value1 = 0;
            }

            return value1;
        } else {
            return 0;
        }
    }

    /**
     * 数组转成String
     * @param array
     * @param split
     * @return
     */
    public static String arrayToString(Object[] array, String split) {
        if(array == null) {
            return "";
        } else {
            StringBuffer str = new StringBuffer("");

            for(int i = 0; i < array.length; ++i) {
                if(i != array.length - 1) {
                    str.append(array[i].toString()).append(split);
                } else {
                    str.append(array[i].toString());
                }
            }

            return str.toString();
        }
    }

    /**
     * 获取文件的绝对路径
     * @return
     */
    public static String getWebInfPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if(filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }

        if(filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }

        if(System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if(!filePath.endsWith("/")) {
            filePath = filePath + "/";
        }

        return filePath;
    }

    /**
     * 同上吧  暂时不知道有啥区别
     * @return
     */
    public static String getRootPath() {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if(filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }

        if(filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }

        if(filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }

        if(System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if(filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.length() - 1);
        }

        return filePath;
    }

    /**
     * 获取输入大小的占用内容大小
     * @param fileSize
     * @return
     */
    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if(dbFileSize >= 1024.0D) {
            if(dbFileSize >= 1048576.0D) {
                if(dbFileSize >= 1.073741824E9D) {
                    temp = df.format(dbFileSize / 1024.0D / 1024.0D / 1024.0D) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024.0D / 1024.0D) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024.0D) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024.0D) + " KB";
        }

        return temp;
    }

    /**
     * 转换utf-8
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        if(str != null && !str.equals("")) {
            try {
                return new String(str.getBytes("ISO8859-1"), "UTF-8");
            } catch (Exception var2) {
                var2.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 转换指定的字符集
     * @param str
     * @param charset
     * @return
     */
    public static String toCharacter(String str, String charset) {
        if(str != null && !str.equals("")) {
            try {
                return new String(str.getBytes("ISO8859-1"), charset);
            } catch (Exception var3) {
                var3.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 去html
     * @param str
     * @return
     */
    public static String replaceEnter(String str) {
        return str == null?null:str.replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 获取html中的内容（去除html标签）
     * @param element
     * @return
     */
    public static String getTxtWithoutHTMLElement(String element) {
        if(null == element) {
            return element;
        } else {
            Pattern pattern = Pattern.compile("<[^<|^>]*>");
            Matcher matcher = pattern.matcher(element);
            StringBuffer txt = new StringBuffer();

            String temp;
            while(matcher.find()) {
                temp = matcher.group();
                if(temp.matches("<[\\s]*>")) {
                    matcher.appendReplacement(txt, temp);
                } else {
                    matcher.appendReplacement(txt, "");
                }
            }

            matcher.appendTail(txt);
            temp = txt.toString().replaceAll("[\r|\n]", "");
            temp = temp.replaceAll("\\s+", " ");
            return temp;
        }
    }

    /**
     * 去除空格
     * @param strtrim
     * @return
     */
    public static String toTrim(String strtrim) {
        return null != strtrim && !strtrim.equals("")?strtrim.trim():"";
    }

    /**
     * 截取添加追加字符
     * @param sourceStr 源字符串
     * @param length 截取的位置
     * @param charactor 追加的字符
     * @return
     */
    public static String cutffStr(String sourceStr, int length, String charactor) {
        String resultStr = sourceStr;
        if(sourceStr != null && !"".equals(sourceStr)) {
            if(sourceStr.length() > length) {
                resultStr = sourceStr.substring(0, length);
                resultStr = resultStr + charactor;
            }

            return resultStr;
        } else {
            return "";
        }
    }

    /**
     * 截串，超过长度 省略
     * @param goodsName
     * @param length
     * @return
     */
    public static String getLength(Object goodsName, int length) {
        if(goodsName == null) {
            return null;
        } else {
            String temp = String.valueOf(goodsName);
            if(temp.length() <= length) {
                return temp;
            } else {
                temp = temp.substring(0, length) + "...";
                return temp;
            }
        }
    }

    /**
     * 随机生成数字
     * @param n
     * @return
     */
    public static String getRandStr(int n) {
        Random random = new Random();
        String sRand = "";

        for(int i = 0; i < n; ++i) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
        }

        return sRand;
    }

    /**
     * 根据系统时间生成随机码
     * @return
     */
    public static String getSysTimeRandom() {
        return System.currentTimeMillis() + "" + (new Random()).nextInt(100);
    }

    /**
     * 根据系统时间生成随机码
     * @param count 长度
     * @return
     */
    public static String getSysTimeRandom(int count) {
        String resultRandom = System.currentTimeMillis() + "" + (new Random()).nextInt(100);
        String resultRandomPro = "";
        int resultCount = resultRandom.length();
        if(count < resultCount) {
            return resultRandom.substring(resultCount - 1 - count, resultCount - 1);
        } else {
            for(int i = 0; i < count - resultCount; ++i) {
                resultRandomPro = resultRandomPro + "0";
            }

            return resultRandomPro + resultRandom;
        }
    }

    /**
     * 随机生成字母和数字的码
     * @param strLength
     * @return
     */
    public static String getRandomString(int strLength) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < strLength; ++i) {
            int charInt;
            char c;
            if(random.nextBoolean()) {
                charInt = 48 + random.nextInt(10);
                c = (char)charInt;
                buffer.append(c);
            } else {
                if(random.nextBoolean()) {
                    charInt = 65 + random.nextInt(26);
                } else {
                    charInt = 97 + random.nextInt(26);
                }

                if(charInt == 79) {
                    charInt = 111;
                }

                c = (char)charInt;
                buffer.append(c);
            }
        }

        return buffer.toString();
    }

    /**
     * 创建指定数量的随机字符串
     * @param length
     * @return
     */
    private String createRandom(int length){
        Random ran = new Random(9);
        String str="";
        for(int i=0;i<length;i++){
            str+=ran.nextInt(9);
        }
        return str;
    }

    /**
     * 时间转为时间戳
     * @param time
     * @return
     */
    public static Timestamp convertToTimestamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date myDate = null;
        Timestamp myTimestamp = null;

        try {
            myDate = sdf.parse(time);
            myTimestamp = new Timestamp(myDate.getTime());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return myTimestamp;
    }

    /**
     * 百分比格式化数字
     * @param n
     * @return
     */
    public static String fromtPercent(double n){
        //获取常规数值格式
        NumberFormat numFormat = NumberFormat.getPercentInstance();
        numFormat.setMaximumIntegerDigits(3); //设置数的 整数 部分所允许的最小位数(如果不足后面补0)
        numFormat.setMaximumFractionDigits(2);//设置数的 小数 部分所允许的最大位数(如果超过会四舍五入)
        return numFormat.format(n);
    }

    /**
     * 货币
     * @param n
     * @return
     */
    public static String fromtCurrency(double n){
        //获取常规数值格式
        NumberFormat numFormat = NumberFormat.getCurrencyInstance();//NumberFormat.getCurrencyInstance(Locale.US);
        return numFormat.format(n);
    }
}

