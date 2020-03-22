package com.raymondmuzzi.my.shop.common.constant;

/**
 * Result status enum
 *
 * @author raymondmuzzi
 * @date 2020-03-22 15:52:53
 */
public enum StatusEnum {
    /** success */
    SUCCESS(200),
    /** nof found the page */
    NOT_FOUND(404),
    /** server side error */
    SERVER_ERROR(500),
    /** auth not validated */
    AUTH_FAILED(403);

    int code;

    StatusEnum(int code) {
        this.code = code;
    }

    public static int getByValue(StatusEnum statusEnum) {
        for (StatusEnum value : StatusEnum.values()) {
            if (statusEnum == value) {
                return value.code;
            }
        }
        throw new RuntimeException("No such StatusEnum");
    }
}
