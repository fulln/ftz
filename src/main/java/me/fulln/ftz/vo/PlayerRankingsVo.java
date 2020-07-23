package me.fulln.ftz.vo;

import lombok.Data;
import lombok.ToString;
import me.fulln.ftz.entity.PlayerRankings;
/**
 * @author fulln
 * @description rank排名的视图层
 * @date  Created in  21:51  2020-07-22.
 **/
@Data
@ToString(callSuper = true)
public class PlayerRankingsVo extends PlayerRankings {

	private String firstName;


	private String middleName;

	private String lastName;
	/**
	 * 姓名
	 */
	private String playerName;

}
