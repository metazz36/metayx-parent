package com.metazz.metayx.common.exception;

import lombok.Data;
import com.metazz.metayx.common.result.ResultCodeEnum;

@Data
public class MetayxException extends RuntimeException{

    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public MetayxException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public MetayxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
