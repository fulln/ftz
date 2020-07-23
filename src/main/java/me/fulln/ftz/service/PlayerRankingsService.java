package me.fulln.ftz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.fulln.ftz.common.GlobalResult;
import me.fulln.ftz.dto.PlayerRankingsDto;
import me.fulln.ftz.entity.PlayerRankings;

/**
 * @author fulln
 * @description 赛球员信息
 * @date  Created in  18:40  2020-07-22.
 **/
public interface PlayerRankingsService extends IService<PlayerRankings> {

	/**
	 * 用来初始化数据
	 * @return
	 */
	GlobalResult insertToDb();

	/**
	 * 查询Page和姓名
	 * @return
	 */
	GlobalResult showList(PlayerRankingsDto dto);
}
