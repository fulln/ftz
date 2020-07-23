package me.fulln.ftz.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.fulln.ftz.common.GlobalResult;
import me.fulln.ftz.dao.PlayerRankingsDao;
import me.fulln.ftz.dto.PlayerRankingsDto;
import me.fulln.ftz.entity.PlayerRankings;
import me.fulln.ftz.enums.GlobalEnums;
import me.fulln.ftz.enums.SnookerHttpEnum;
import me.fulln.ftz.service.PlayerRankingsService;
import me.fulln.ftz.utils.httpUtil;
import me.fulln.ftz.vo.PlayerRankingsVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fulln
 * @description 球员相关信息
 * @date Created in  18:48  2020-07-22.
 **/
@Service
public class PlayerRankingsServiceImpl extends ServiceImpl<PlayerRankingsDao, PlayerRankings> implements PlayerRankingsService {

	@Resource
	private PlayerRankingsDao playerRankingsDao;

	/**
	 * 用来初始化数据
	 *
	 * @return
	 */
	@Override
	public GlobalResult insertToDb() {
		String p = httpUtil.ClientGetRequest(SnookerHttpEnum.PLAYER_RANKINGS.getUrl("MoneyRankings", 2019));
		Assert.notNull(p, "请求数据为空");
		List<PlayerRankings> playerRankings = JSONArray.parseArray(p, PlayerRankings.class);
		if (saveBatch(playerRankings)) {
			return GlobalResult.results(GlobalEnums.SYS_SUCCESS);
		} else {
			return GlobalResult.results(GlobalEnums.SYS_ERROR);
		}
	}


	/**
	 * 查询Page和姓名
	 *
	 * @return
	 */
	@Override
	public GlobalResult showList(PlayerRankingsDto dto) {
		IPage<PlayerRankingsVo> userPage = new Page<>(dto.getPageNo(), dto.getPageSize());
		IPage<PlayerRankingsVo> records = playerRankingsDao.selectListAndName(userPage, dto);
		records.getRecords().forEach(

				li -> {
					StringBuilder sb = new StringBuilder();
					if(!StringUtils.isEmpty(li.getFirstName())){
						sb.append(li.getFirstName());
					}
					if(!StringUtils.isEmpty(li.getMiddleName())){
						sb.append("·").append(li.getMiddleName());
					}
					if(!StringUtils.isEmpty(li.getLastName())){
						sb.append("·").append(li.getLastName());
					}
					li.setPlayerName(sb.toString());
				}
		);
		return GlobalResult.results(GlobalEnums.SYS_SUCCESS, records);
	}
}
