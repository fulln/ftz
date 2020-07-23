package me.fulln.ftz.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;


/**
 * 赛季球员表
 *
 * @author fulln
 * @date 2020-07-22
 */
@Data
public class SeasonPlayer implements Serializable{

	/**
	 * 主键
	 */
  private Long id;
	/**
	 * 用户id
	 */
  private Long type;
	/**
	 * 名
	 */
  private String firstName;
	/**
	 * 名
	 */
  private String middleName;
	/**
	 * 姓
	 */
  private String lastName;
	/**
	 * 球队名称
	 */
  private String teamName;
	/**
	 * 球队编号
	 */
  private Long teamNumber;
	/**
	 * 球队赛季
	 */
  private Long teamSeason;
	/**
	 * 简称
	 */
  private String shortName;
	/**
	 * 国籍
	 */
  private String nationality;
	/**
	 * 性别
	 */
  private String sex;
	/**
	 * 主页
	 */
  private String bioPage;
	/**
	 * 生日
	 */
  private String born;
	/**
	 * 推特
	 */
  private String twitter;
	/**
	 * 姓名开始
	 */
  private Integer surnameFirst;
	/**
	 * 驾照
	 */
  private String license;
	/**
	 * 俱乐部
	 */
  private String club;
	/**
	 * 地址
	 */
  private String url;
	/**
	 * 照片
	 */
  private String photo;
	/**
	 * 照片来源
	 */
  private String photoSource;
	/**
	 * 第一赛季
	 */
  private Long firstSeasonAsPro;
	/**
	 * 最后一季
	 */
  private Long lastSeasonAsPro;
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
  private Integer deleteFlag;
	/**
	 * 翻译的id
	 */
	private Integer translateId;
}
