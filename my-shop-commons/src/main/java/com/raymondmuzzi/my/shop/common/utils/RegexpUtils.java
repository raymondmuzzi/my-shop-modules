package com.raymondmuzzi.my.shop.common.utils;

/**
 * Phone number and email check utils
 *
 * @author raymondmuzzi
 * @date 2020-03-22 18:16:56
 */
public class RegexpUtils {

    /**
     * Check phone number
     */
    public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * Check email address
     */
    public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

    /**
     * Check phone number method
     *
     * @param phone the input phone number
     * @return the check result
     */
    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE);
    }

    /**
     * Check phone number method
     *
     * @param email the input email
     * @return the checke result
     */
    public static boolean checkEmail(String email) {
        return email.matches(EMAIL);
    }
}
