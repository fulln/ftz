package me.fulln.ftz.dto;

import lombok.Data;
import me.fulln.ftz.entity.PlayerRankings;
/**
 * @author fulln
 * @description 数据交换层实体
 * @date  Created in  22:00  2020-07-22.
 **/

@Data
public class PlayerRankingsDto extends PlayerRankings {
	private Integer pageSize;
	private Integer pageNo;
	private String  playerName;
}
