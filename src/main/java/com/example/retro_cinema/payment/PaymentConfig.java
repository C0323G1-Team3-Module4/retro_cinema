package com.example.retro_cinema.payment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PaymentConfig {
    public String vnp_payUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_Returnurl = "http://localhost:8080/payment/return";
    public static String vnp_TmnCode = "GH5LJFLR";
    public static String vnp_HashSecret = "FLTDDGNTBDWWJVPGYZKMAWBEFKDVVQJR";
    public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    public static String vnp_Version = "2.1.0";
    public static String vnp_Command = "pay";

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
