package com.xrpt.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author by wjx
 * @date 2020/7/18
 * @DESC: 支付宝配置类
 */

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400749494";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCiIMaBbmGKpamBV5Yq3UGQfsHxMnQYVEkgRW8mWHrptj8E7gvj117XWab48VoVqgMqX8Oqi0J8jvJ+xASxE3rcIx9Th4YSXVWcoSY7/WnlNbS9InyxqDfcWRsmoCbcH0GyCLooDn4WHCbYgAmzptSw+uhpl4bjxdfZdpv8OvShR+8YiLti/ZrdqmSgYrIJHzNYlsI51SixipaMST1vpmp82ty8jBdmmSXi2BnqBHDMNnnw3JvKLjZF0k/VD1C88M+a7uf3CrSrtgoGNWDDlPTdl2JYWww9PZXdtCOeQuaXtR17p1ftEg9KSOTmvYs9GDbcNSLvrxUKU2xmjXyF5Mv1AgMBAAECggEAIt0jcr/Wz+bR7ZN1ImCT8cugTwrHdlfhpcPoo/lQ7SMYqCm8etFcfriztELKWXBG3/zZ6bmqoXhn4ySp0Z5bxCz1VpQFG7vgZh8tRqc292g2Jpnc8KM45/RtZz/3O0ypZuyDg4uiR4TkQZg+/df5UtmR0usXwbFQul3MK1BOPMQOaRXdvTcxa87ZDxnCBUMrBfhuIaxZdvdDPzpQzAag1WLN4K4AvJOMDyZQ61TF/drkmeHjMaPfysKyj7pE1UGqHxqJRpD2epM/OEcOjY0iOc3EPEPqlnwlJct+9AWqYT65A0uJoIkSqlnW75wtlnAR0SodTa5ljbaYW4aziKl84QKBgQD/GkjLQ6NRFWiegRO+EBHKMmJ3bjxaEI50c2mr65nXvNVykxt8jIanjF7SOLsC6adnxyIw8LoJVBeXYKBccUhEkauPUGhAX6uWrnQl8lfOr25V78GJoaz704gJXud+KgZ5Iol6GaQ++yCDmacUflPiihc0UVB8xgyg8Z4RUrzHCQKBgQCissTa1OCSIjSSAfml05MQX8d7T/BOQDA1YSj0axUmUpn7WKcqRcSEgFG3H5KJpd8ds67O+9bJJvGunquUOq7k3dYvhfzXvasNLEyKYyoUc84mXalaT3CjThRm1w9vNKRfwa7OjDo4qOJGpRPA8C6bwngO/hGEJ++10evI3tDMjQKBgEmYcKXLhqNO1NbdkC+F/UvjC0s/T0QAgMiRv0us9b4qqT2buRNOi05tDbICUdl+RSCIb5HE8TbdvsyaQMlKmTnG+MKoxNrFfwBCphmId9KWA/Pg7mnhbcFc/mMNsSVRxHpP/29tVFgjJPcybeRYqaSyiA085EaK8HvCGQMQZovZAoGAX0Hx1fobfhP+2ZnEdB27FjQt1HoKRZZ5Y1d+phRoLqbgNvRB85MjqzyCG4ilwGfYiUhhyhhTOjTN/iIybTgqnB9yPyLg1ePb2XudWM0UnyLqiTX/aLvoRAT8FvZFuCOHNfVthss9b10aFqiiNy2o7FNg/c4trUXAqKC+vz4N3CUCgYAYl5fnc/5kZ9CHTG1/KonTbkN/LhT5wkZ3JREbEOcjVRZURlZW5IW1rxxXIAQRq1fRnYjsr4SpO7vcZ96/TdsNcWOKiIH/+UEfpp0j/nUbHY0bYjaX4NZKkSVTN9ZRV6N2ivW0u6JxWbfW0W3Oeyo9qe4aPtRI+LzBspLz3WWmEw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmXRJvJZhSDjsuMjbsMw7YDxf/y552GJ9z7U5H4SVWq5WziR3E/9wY+ysTQDyf3dDRL95glYe16QAcB7bjPVdFv8iTqA1XsJLOj0tl4EvK+/0OKTU/+JflZijOIVXYEaEDKb9dumQatQg5bGcbSJ1G1o/giIA5D/VxnstSrNI1b1dtU85mNtm1bJb9OCo88NPD/2AvneFuN6F/nXjYTyZAtR2VypH93wGXQKCFwmDKlMoB0QjyUu4MtCkHtlvhrXR4T34dXe2oL9JQwDdze0kc2FrTRD20N6iEIKEWvQ2+ArsVUFYO0FqToK+cgMPwymYAsHGoboCgpybyQLN7YJREQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/xrpt/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/xrpt/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";

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
