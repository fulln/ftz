package me.fulln.ftz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 球员排行
 *
 * @author fulln
 * @date 2020-07-22
 */
@Data
public class PlayerRankings implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date createSysTm;
	/**
	 * 更新时间
	 */
	private Date updateSysTm;
	/**
	 * 逻辑删除标记:0未删除,1已删除
	 */
	@TableField(select = false)
	@TableLogic
	private Integer deleteFlag;
	/**
	 * 排名
	 */
	private Long position;
	/**
	 * 球员id
	 */
	private Long playerId;
	/**
	 * 赛季
	 */
	private String season;
	/**
	 * 身价
	 */
	private Long sum;
	/**
	 * 排名类型
	 */
	private String type;

}
