package me.fulln.ftz.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.fulln.ftz.common.GlobalResult;
import me.fulln.ftz.dao.SeasonPlayerDao;
import me.fulln.ftz.entity.SeasonPlayer;
import me.fulln.ftz.enums.GlobalEnums;
import me.fulln.ftz.enums.SnookerHttpEnum;
import me.fulln.ftz.service.SeasonPlayerService;
import me.fulln.ftz.utils.httpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author fulln
 * @description 球员相关信息
 * @date  Created in  18:48  2020-07-22.
 **/
@Service
public class SeasonPlayerServiceImpl extends ServiceImpl<SeasonPlayerDao, SeasonPlayer> implements SeasonPlayerService {

	/**
	 * 用来初始化数据
	 * @return
	 */
	@Override
	public GlobalResult insertToDb() {
		String p = httpUtil.ClientGetRequest(SnookerHttpEnum.SEASON_PLAYER.getUrl(10, "p", 2019));
		Assert.notNull(p, "请求数据为空");
		List<SeasonPlayer> seasonPlayers = JSONArray.parseArray(p, SeasonPlayer.class);
		if (saveBatch(seasonPlayers)) {
			return GlobalResult.results(GlobalEnums.SYS_SUCCESS);
		}else {
			return GlobalResult.results(GlobalEnums.SYS_ERROR);
		}

	}
}
