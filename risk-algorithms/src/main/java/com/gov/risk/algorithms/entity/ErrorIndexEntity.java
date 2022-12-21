package com.gov.risk.algorithms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 指标依据与指标表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-12-21 19:20:01
 */
@Data
@TableName("error_index")
public class ErrorIndexEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 异常指标ID
	 */
	@TableId
	private Integer errorIndexId;
	/**
	 * 指标ID
	 */
	private Integer indexId;
	/**
	 * 风险等级
	 */
	private String level;
	/**
	 * 指标状态
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
