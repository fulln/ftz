package me.fulln.ftz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.fulln.ftz.common.GlobalResult;
import me.fulln.ftz.entity.SeasonPlayer;

/**
 * @author fulln
 * @description 赛球员信息
 * @date  Created in  18:40  2020-07-22.
 **/
public interface SeasonPlayerService extends IService<SeasonPlayer> {

	/**
	 * 用来初始化数据
	 * @return
	 */
	GlobalResult insertToDb();

}
