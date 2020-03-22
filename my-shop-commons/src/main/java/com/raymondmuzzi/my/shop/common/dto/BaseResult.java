package com.raymondmuzzi.my.shop.common.dto;

import com.raymondmuzzi.my.shop.common.constant.StatusEnum;

import java.io.Serializable;

/**
 * Self-defined return result
 *
 * @author raymondmuzzi
 * @date 2020-03-22 15:48:25
 */
public class BaseResult implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int status;
    private String message;

    /**
     * Success status
     *
     * @return success base result
     */
    public static BaseResult success() {
        return BaseResult.createBaseResult(
                StatusEnum.getByValue(StatusEnum.SUCCESS),
                StatusEnum.SUCCESS.toString());
    }

    /**
     * Success status with specific message
     *
     * @param message success message
     * @return
     */
    public static BaseResult success(String message) {
        return BaseResult.createBaseResult(
                StatusEnum.getByValue(StatusEnum.SUCCESS),
                message);
    }

    /**
     * Fail status
     *
     * @return fail base result
     */
    public static BaseResult fail() {
        return BaseResult.createBaseResult(
                StatusEnum.getByValue(StatusEnum.SERVER_ERROR),
                StatusEnum.SERVER_ERROR.toString());
    }

    /**
     * Fail status with specific message
     *
     * @param message fail message
     * @return
     */
    public static BaseResult fail(String message) {
        return BaseResult.createBaseResult(
                StatusEnum.getByValue(StatusEnum.SERVER_ERROR),
                message);
    }

    /**
     * Fail status with specific status code and message
     *
     * @param status  status code
     * @param message fail message
     * @return
     */
    public static BaseResult fail(int status, String message) {
        return BaseResult.createBaseResult(
                status,
                message);
    }

    /**
     * Create the base result object
     *
     * @param status  status code
     * @param message base result message
     * @return
     */
    private static BaseResult createBaseResult(int status, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }

    /** Getter/Setter begins */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** Getter/Setter ends */

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
