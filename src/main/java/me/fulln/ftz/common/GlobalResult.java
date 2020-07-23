package me.fulln.ftz.common;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import me.fulln.ftz.enums.GlobalEnums;

/**
 * @Author: fulln
 * @Description: 通用当前返回结果
 * @Date: Created in 2018/5/2 0002
 */
@Data
public class GlobalResult<T> {

	private String message;

	private Integer code;

	private T data;

	public static GlobalResult results(GlobalEnums enums, Object... value) {
		GlobalResult re = results(enums);
		if (re.code < 0) {
			re.setMessage(JSON.toJSONString(value));
		} else {
			if(value.length == 1){
				re.setData(value[0]);
			}else {
				re.setData(value);
			}
		}
		return re;
	}

	public static GlobalResult results(GlobalEnums enums) {
		GlobalResult re = new GlobalResult();
		re.setCode(enums.getCode());
		re.setMessage(enums.getMessage());
		return re;
	}
}
