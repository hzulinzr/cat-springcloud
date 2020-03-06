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
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCWJ93NoDVP/SOwjY6E3WU1E9McLr7AZYE2UvWkxabWCgMJlzaYMt7J9vfBol2LUFdLpz9PIUm6E9NsXbrYDrd0hAmHU/LTqaTkPRQG0y6ymnQmSEk67OPX5nCMQzZNlWiY8nkSjyWj2mztbv5oaoHHTEXkhNqcT4LzehGPZNoiuFkxyDtbvYwHKK+elV6HAYryO4wKkVzoU5PYxJKbT4WjF58VgxcJaelfhMwO7ZWBIEJWTyitAx4Gt8MOKnf6Z2IizlnG3SopBqauvE3CYlzSGqK77yQXY8aqV1V6DU724F7IO5tIm7wrpj//I0NVeAHqgMIfNFc4w0O7On7gVHnfAgMBAAECggEAORWQ4kJ84hr3fYBjZSmR91rBEF7JD/XG6LMjRfAKXCbUPsoo7DWysw9b41Zpv7PcbBt6Gwgpsj8lSnZbuRXnMrmbscH9d/BvioGZ4npYr3e+nmsuw+MZP5QQx5Ipy9b4O+U6mA6pnaBFdwKSX3nWOsFJdPX48iHcQ9ntYRlTQI1BzDxLZz407u3MQZplVVj/UZtJvdvaTZigSY9clMBVm6xGP5PA82QOv1Zy1PJpnfMx0mLIhk8il492bmWYU+zHER+nFohIlxIfANTJaafA+RazAEdYsRkK+lsM3doW56d0k4axaER9toLcIBb5ejEdp2eGbCnbh/SXw6/qiVKGyQKBgQD4C8mWKRdwoDze8EfNrFBtaAaWgA8v26H6zHXTF09Rcnzej6ZnfMM3+qczQzPWe9y7M7oW7aBLSnoq7zQ3XEhbekxPwZmnb9OJqo8WUMomhuFNimZmH5zLDfMgNVDIOwKhD2Om9RHtD7zlztvtPZmodHBAq3rGjiILlG5q6Ntr6wKBgQCa+H8Qray2RlTyRpoFtBXlN3zHAR3B4z7RV7ApzwTazuF8SeCAqdT1pXZNG0gpR7v5FqL45D7nLN+2VKiRA4C9oADRw22q3SzYSasNG0CpxoQqCft7iS48IiZ2teeu+8CETk9umTQTPfoyV8jlvDCCaWdnDxbauEtNn4/QLjTw3QKBgQCkAf8/d1oVUQ4OhqejeY0QWhkJCBGCnmGC2BAMDDyY6O9hpW/su1TFPcmyHnw3FF0FtpUTtfkn703J97H57N23Bq1Qiv0S9eDUBN9pbO/M+6AbSiic28grhsS8BiSs0D/TyL4mVaKNsbs/5EAMpI9mD99tEzUx9ibSq0k7DU6QEwKBgAJTqNS/RtCVzcC1fqqHQaznYGA8jA3VM6RBBMwb557BbHFJSq2j3yaxDU2rEzJLeW8L//ZstuURr2o1azKCMvSl6l076fGe0IWO1ZqkxhvGd0WKUNRSAve0K4+su4XNEd7qrhajBj0juRX4a3PHl5LdskfB2j3+dmL7+lTN3M2hAoGBAMEuYA9Eh1rvyyWGemhoZiB3J9iYt8ql7azD4rjaNNAcJmnDg5MRJHOKkYjhCtd5A77udfyUUMT9qHFFXQqwJFNACHLt8xe+n6ISsXyRFBSyCRg3/+n3wVTlWsdh5I9dFac34xwnofx5lKJAUUIJ25lxU6cq3tISzQ6GBBSEAlyr";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjloIO2HxhOsghl0lyRS/ntKo70MhLZhWfoiWo1EwJEQV6byvZ5fNZJTHSJX4ySmYp+WcPZ9ALUOPpye4R70eR6JdZ9xWHAlSdbLeOr9P7qyJDZx8jndJbu37C4tB9DXp9f+gpAKdVRbqLh0mycvKk3MJpq7GW8buph8Rp0M14/p8SX4h7sUBvT5pFlMu1ah88lQN4jrjVsaybatB7DegUOp2H1epH0sboOwZfMfqGRdFYgxzY9xpOI+xPlOkZIxHMDhtCMWkJnybzMkKBkAEah+m6fsH+LmrZJ6mthBmHTrkJzpAqVUSfTuS/k2hHd9SScz0G5Fb1QZmZ+eFZPb46wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.0.100:8081/order/finish";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:5002/paymentSuccessful";

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

