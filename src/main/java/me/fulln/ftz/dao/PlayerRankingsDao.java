package me.fulln.ftz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.fulln.ftz.dto.PlayerRankingsDto;
import me.fulln.ftz.entity.PlayerRankings;
import me.fulln.ftz.vo.PlayerRankingsVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author fulln
 * @description dao层
 * @date  Created in  18:31  2020-07-22.
 **/
public interface PlayerRankingsDao extends BaseMapper<PlayerRankings> {
	/**
	 *根据用户名做list查询
	 * @param userPage
	 * @param dto
	 * @return
	 */
	IPage<PlayerRankingsVo> selectListAndName(IPage<PlayerRankingsVo> userPage,@Param("dto") PlayerRankingsDto dto);
}