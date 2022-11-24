package com.gov.risk.indicator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 指标依据表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Data
@TableName("colling")
public class CollingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 指标依据ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer collingId;
	/**
	 * 指标依据名称
	 */
	private String collingName;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
