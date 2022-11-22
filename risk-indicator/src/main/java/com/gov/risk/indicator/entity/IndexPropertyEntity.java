package com.gov.risk.indicator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 指标属性表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Data
@TableName("index_property")
public class IndexPropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 观测指标ID
	 */
	@TableId
	private Integer indexPropertyId;
	/**
	 * 指标ID
	 */
	private Integer indexId;
	/**
	 * 用于表示是否是同一张表
	 */
	private String identification;
	/**
	 * 标准值
	 */
	private String standardValue;
	/**
	 * 标准值补充/修正
	 */
	private String standardValueSupplement;
	/**
	 * 可备注
	 */
	private String extra;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
