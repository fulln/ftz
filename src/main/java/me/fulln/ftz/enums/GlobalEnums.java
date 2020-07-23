package me.fulln.ftz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @AUthor: fulln
 * @Description: 返回结果枚举
 * @Date : Created in  18:35  2018/8/4.
 */
@AllArgsConstructor
@Getter
public enum GlobalEnums  {
    /**
     * 系统异常
     */
    SYS_ERROR(-9999, "sys.error"),
    SYS_SUCCESS(1, "sys.success"),



    ;

    private int code;
    private String message;


}
