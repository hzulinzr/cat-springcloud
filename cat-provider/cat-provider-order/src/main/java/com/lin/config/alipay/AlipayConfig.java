package com.lin.config.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101700711404";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkrsn5JJcsFnI9Uhir4h9eGBEk2Abcd/n4wrXkuyYGUI1U+64IYy3FpmG0gAhgC5SSn0qqvJdjL7xFSEoHlYJNbbVkgkDv4FcSHYLKde/wbEcwgKFiVyEXXI5a6J+tq711yLDJxj812WfalfxZhtY2UASza9xrNn/MIzyCf6VNelFidApQOxDgxRvZxTm5IcrCb0IL3oj6mWMX5GFShOIo0/shB/ojsA90nJrmGp/7vh4Ayw6ivF8DrQDgahJeha/e7nKBe2TKdDJoDUls5OjKDaMh5FJL24m8MIEjsCYbvNN4P/YTmUOfvWKVUs2zX4eTUOWRyjPXif0/Dsfw1fINAgMBAAECggEACDT1t8bNCdNsigXHWPmB2qyUXiOt49rOyEjby7RP5syo3m1UdPKMW2c5Q0eGI1MwakYp8GxJlxXP6hv6JHqc7cLj4UhkeA/UBwEVfYV3GaIrwg0G/eGdso5YQcuJ+Zuva2k+9i89Ay/8bvY+hy+jCT3fnhln+KfUopW29EHsOPok7T37fEXgUDPtZFtEUzTlQ3T8uWq6i9Aq6Jg/6ycdK4EKSVQJXFmXAPuseEDa1Y+E/76FU6SBATRDnmJ3qNzNNK2BZtZwojJ2E3TxtCpMh7dVw8CC494N57URJuHjaXE0EZGQkHv0Tt1K8JZN4Ug/rWkfMuDRPD6JuQON4NdBQQKBgQDmzcaRko22ZZauZI8KM3JSxBLdj9KDlx6KOegPV9kPDS1NNSbpdjlJOtsQiFvrKWSRiPQJAhCZ2R1m9lRl08nE6o5xyzx+T/vcgrARluIpW3XlG/2n3e/aOXzpkXL7io0kZPapGioZc9/mnBgRbFL9xi7sqT+W+KcdIGlxWLXnqQKBgQC2qSSOqxJsCGjGyulRCH1sXX+mm8G3bt4uPsbSkx/Og2oG753CG3CN5sX5DJe+MfMi3XZeqIXIKu6Jj09cVHTlqlNMZz9pfEP0PJpsRA+MUytM6LiGqCnwlmDAfccZD842njXZ512LdZOpmiGdvY3zzWIy6XsVf27e5pR+ie1lxQKBgQDE2OYtjBfvY49zJ3LT2XL84hvQ+fQqpT232JFyXJNVAgEMI3QPk/Rh35+s5w+2bnRGp86yzB8KiCFKincK7endV8urhBIRDPibZp7yMYSsj0Pq5sedX21M6tD9iD7QCKg+TRoOubEESk7BKYpheckChf/JesQwZ9xnvBgSQv3KGQKBgH9YKK52Qd+jdTiymjruSlLUVHxZNpKl7qwbBA4mmBQntjGjuD5tVuj3LKpWxyvIMxPphe60WqCUnby1dsSN9Plw56XqjWEDS3TpE1fLk3NnQFbcIFlwv9yFWmqYxceJJ2EYp76E+MiTbiDd3fXVAzQnygBomnEAaNFpu7kEe8dpAoGBAND2bZv68oRnWnpXlzapU0DkWZjeUMP+7vOE5VhKhG3M2lHNpO5UmhHBtf0WPOsnEHMXDyYS8GHACu5iN5EaHv43xvB6EM/wPj8lcg5sSYH6bj7xrn0aEEQUn8ddHVe8hTNqlC+nG1AeaE/SCbbFT1nlxhPcgcBoP0dhEPYOqvlN";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjloIO2HxhOsghl0lyRS/ntKo70MhLZhWfoiWo1EwJEQV6byvZ5fNZJTHSJX4ySmYp+WcPZ9ALUOPpye4R70eR6JdZ9xWHAlSdbLeOr9P7qyJDZx8jndJbu37C4tB9DXp9f+gpAKdVRbqLh0mycvKk3MJpq7GW8buph8Rp0M14/p8SX4h7sUBvT5pFlMu1ah88lQN4jrjVsaybatB7DegUOp2H1epH0sboOwZfMfqGRdFYgxzY9xpOI+xPlOkZIxHMDhtCMWkJnybzMkKBkAEah+m6fsH+LmrZJ6mthBmHTrkJzpAqVUSfTuS/k2hHd9SScz0G5Fb1QZmZ+eFZPb46wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8081/order/finish";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8081";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

