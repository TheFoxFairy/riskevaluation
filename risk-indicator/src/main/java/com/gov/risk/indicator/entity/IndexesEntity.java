package com.gov.risk.indicator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 指标表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-19 17:06:37
 */
@Data
@TableName("indexes")
public class IndexesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 指标ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer indexId;
	/**
	 * 指标类型
	 */
	private String indexType;
	/**
	 * 指标名称
	 */
	private String indexName;
	/**
	 * 指标父ID
	 */
	private Integer indexParentId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@TableField(exist = false)
	List<IndexesEntity> children;

	@TableField(exist = false)
	List<CollingEntity> collingEntities;


	@TableField(exist = false) // 只有观测指标有
	IndexPropertyEntity indexPropertyEntity;
}
