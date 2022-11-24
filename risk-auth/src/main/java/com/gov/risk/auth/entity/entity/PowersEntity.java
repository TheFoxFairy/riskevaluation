package com.gov.risk.auth.entity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 权限表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Data
@TableName("powers")
public class PowersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 权限ID
	 */
	@TableId
	private Integer powerId;
	/**
	 * 权限名称
	 */
	private String powerName;
	/**
	 * 权限URL
	 */
	private String powerUrl;
	/**
	 * 权限类型
	 */
	private String powerType;
	/**
	 * 权限图标
	 */
	private String powerIcon;
	/**
	 * 父权限ID
	 */
	private Integer powerParentId;

	@TableField(exist = false)
	List<PowersEntity> children;

}
