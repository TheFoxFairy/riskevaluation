package com.gov.risk.algorithms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 指标依据与指标表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Data
@TableName("colling_indexes")
public class CollingIndexesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 指标依据ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer collingId;
	/**
	 * 指标ID
	 */
	private Integer indexId;

}
