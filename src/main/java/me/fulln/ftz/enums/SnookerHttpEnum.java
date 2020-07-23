package me.fulln.ftz.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.List;

/**
 * snooker的 api
 * @author fulln
 * @date   Created in  2020-07-22  18:16.
 * @param
 * @return
 **/
@AllArgsConstructor
@Getter
public enum SnookerHttpEnum {
	/**
	 *
	 */
	PLAYER_RANKINGS(1, "http://api.snooker.org/?rt={0}&s={1}", "", "球员排名"),
	SEASON_PLAYER(2, "http://api.snooker.org/?t={0}&st={1}&s={2}", "", "赛季球员信息"),
	;
	private int code;
	private String url;
	private String params;
	private String desc;

	/**
	 * @param obj2 参数
	 * @return
	 */
	public String getUrl(Object... obj2) {
		for (Object o : obj2) {
			if (o instanceof List) {
				o = JSONArray.toJSONString(o);
			} else {
				o = JSON.toJSONString(o);
			}
		}
		return MessageFormat.format(url, obj2).replaceAll(",","");
	}


	public String getParams(Object... obj2) {
		for (Object o : obj2) {
			if (o instanceof List) {
				o = JSONArray.toJSONString(o);
			} else {
				o = JSON.toJSONString(o);
			}
		}
		return "{" + MessageFormat.format(params, obj2) + "}";
	}
}
