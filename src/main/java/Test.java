/*
 *  @created 13/01/2024 - 8:06 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.payment.VNPay;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {
        VNPay vnPay = new VNPay();
        System.out.println(vnPay.getPaymentURL(12000, "THANH TOAN HOA DON 12345", "192.168.1.11", "us", "475837505"));
//        System.out.println(StringUtils.generateSHA256String(PropertiesUtils.getInstance().getProperty("vnp_HashSecret") + "hahaha"));
    }
}
