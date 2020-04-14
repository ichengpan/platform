package com.iviui.platform.framework.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 返回结果VO对象
 * @Author ChengPan
 * @Date2020/4/10 上午 10:09
 * @Version V1.0
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    @ApiModelProperty(value = "状态码,成功200", example = "200")
    private Integer code =200;

    @ApiModelProperty(value = "状态码为200时，msg为成功， 否则为错误信息", example = "成功")
    private String msg = "成功";

    @ApiModelProperty("返回数据")
    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(T data) {
        this.data = data;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param resultCode 返回信息
     * @return ResultVO
     */
    public static <T> ResultVO getSuccess(ResultCode resultCode) {
        return new ResultVO(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 请求成功  状态码 1
     *
     * @param resultCode  返回信息
     * @param data 返回对象
     * @param <T>  类型
     * @return ResultVO
     */
    public static <T> ResultVO getSuccess(ResultCode resultCode, T data) {
        return new ResultVO(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> ResultVO setSuccessData(T data) {
        return new ResultVO(data);
    }

    /**
     * 请求失败   状态码 0
     *
     * @param resultCode 返回信息
     * @param <T> 类型
     * @return ResultVO
     */
    public static <T> ResultVO getFailed(ResultCode resultCode) {
        return new ResultVO(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 请求失败  状态 0
     *
     * @param resultCode  返回信息
     * @param data 返回数据
     * @param <T>  类型
     * @return ResultVO
     */
    public static <T> ResultVO getFailed(ResultCode resultCode, T data) {
        return new ResultVO(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
