package com.filmbooking.payment;

/*
 *  @created 13/01/2024 - 2:07 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class VNPay {
    private final PropertiesUtils propertiesUtils;
    private    Map<String, String> vnp_Params;

    public VNPay() {
        vnp_Params = new HashMap<>();
        propertiesUtils = PropertiesUtils.getInstance();
        defaultConfig();
    }

    /**
     * Default config for vnp_Params
     */
    private void defaultConfig() {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TmnCode = propertiesUtils.getProperty("vnp_TmnCode");
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_OrderType", "190000");
        vnp_Params.put("vnp_ReturnUrl", propertiesUtils.getProperty("vnp_ReturnUrl"));
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
    }

    /**
     * Add amount of this payment to vnp_Params
     * @param amount   amount of this payment
     * @return {@link VNPay} to chain
     */
    public VNPay addAmount(double amount) {
        int intAmount = (int) amount * 100;
        vnp_Params.put("vnp_Amount", String.valueOf(intAmount));
        return this;
    }

    /**
     * Add transaction reference to vnp_Params
     * <br>
     * This is a unique reference for this transaction
     * <br>
     * If transaction successful, the current vnp_TxnRef is cannot be used again
     * @param vnp_TxnRef  transaction reference
     * @return {@link VNPay} to chain
     */
    public VNPay addTxnRef(String vnp_TxnRef) {
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        return this;
    }

    /**
     * Add order info to vnp_Params
     * @param orderInfo order info
     * @return {@link VNPay} to chain
     */
    public VNPay addOrderInfo(String orderInfo) {
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        return this;
    }

    /**
     * Add locale to vnp_Params
     * <br>
     * This is the language of the payment page
     * @param req  request to get locale from session
     * @return {@link VNPay} to chain
     */
    public VNPay addLocale(HttpServletRequest req) {
        String locate = (String) req.getSession(false).getAttribute("lang");

        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        return this;
    }

    /**
     * Add customer IP to vnp_Params
     * @param req  request to get customer IP
     * @return {@link VNPay} to chain
     */
    public VNPay addCustomerIP(HttpServletRequest req) {
        String customerIP = req.getRemoteAddr();
        vnp_Params.put("vnp_IpAddr", customerIP);
        return this;
    }

    /**
     * Get payment URL from vnp_Params
     * @return payment URL
     */
    public String getPaymentURL() {
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = StringUtils.hmacSHA512(propertiesUtils.getProperty("vnp_HashSecret"), hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        return propertiesUtils.getProperty("vnp_Url") + "?" + queryUrl;
    }

//    public String getPaymentURL(double amount, String orderInfo, String customerIP, String locate, String vnp_TxnRef) {
////        String vnp_Version = "2.1.0";
////        String vnp_Command = "pay";
//
////        String vnp_TmnCode = propertiesUtils.getProperty("vnp_TmnCode");
//
//        int intAmount = (int) amount * 100;
//        System.out.println(intAmount);
//
////        vnp_Params.put("vnp_Version", vnp_Version);
////        vnp_Params.put("vnp_Command", vnp_Command);
////        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.put("vnp_Amount", String.valueOf(intAmount));
////        vnp_Params.put("vnp_CurrCode", "VND");
//        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.put("vnp_OrderInfo", orderInfo);
////        vnp_Params.put("vnp_OrderType", "190000");
//
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
////        vnp_Params.put("vnp_ReturnUrl", propertiesUtils.getProperty("vnp_ReturnUrl"));
//        vnp_Params.put("vnp_IpAddr", customerIP);
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//
//        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
//        cld.add(Calendar.MINUTE, 15);
//        String vnp_ExpireDate = formatter.format(cld.getTime());
//        //Add Params of 2.1.0 Version
//        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
//        //Billing
////        vnp_Params.put("vnp_Bill_Mobile", req.getParameter("txt_billing_mobile"));
////        vnp_Params.put("vnp_Bill_Email", req.getParameter("txt_billing_email"));
////        String fullName = (req.getParameter("txt_billing_fullname")).trim();
////        if (fullName != null && !fullName.isEmpty()) {
////            int idx = fullName.indexOf(' ');
////            String firstName = fullName.substring(0, idx);
////            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
////            vnp_Params.put("vnp_Bill_FirstName", firstName);
////            vnp_Params.put("vnp_Bill_LastName", lastName);
////
////        }
////        vnp_Params.put("vnp_Bill_Address", req.getParameter("txt_inv_addr1"));
////        vnp_Params.put("vnp_Bill_City", req.getParameter("txt_bill_city"));
////        vnp_Params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
////        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
////            vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
////        }
//        // Invoice
////        vnp_Params.put("vnp_Inv_Phone", req.getParameter("txt_inv_mobile"));
////        vnp_Params.put("vnp_Inv_Email", req.getParameter("txt_inv_email"));
////        vnp_Params.put("vnp_Inv_Customer", req.getParameter("txt_inv_customer"));
////        vnp_Params.put("vnp_Inv_Address", req.getParameter("txt_inv_addr1"));
////        vnp_Params.put("vnp_Inv_Company", req.getParameter("txt_inv_company"));
////        vnp_Params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
////        vnp_Params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));
//        //Build data to hash and querystring
//        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
//        Collections.sort(fieldNames);
//        StringBuilder hashData = new StringBuilder();
//        StringBuilder query = new StringBuilder();
//        Iterator<String> itr = fieldNames.iterator();
//        while (itr.hasNext()) {
//            String fieldName = (String) itr.next();
//            String fieldValue = (String) vnp_Params.get(fieldName);
//            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
//                //Build hash data
//                hashData.append(fieldName);
//                hashData.append('=');
//                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
//                //Build query
//                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
//                query.append('=');
//                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
//                if (itr.hasNext()) {
//                    query.append('&');
//                    hashData.append('&');
//                }
//            }
//        }
//        String queryUrl = query.toString();
//        String vnp_SecureHash = StringUtils.hmacSHA512(propertiesUtils.getProperty("vnp_HashSecret"), hashData.toString());
//        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
//
//        return propertiesUtils.getProperty("vnp_Url") + "?" + queryUrl;
//    }
}
