package com.gov.risk.auth.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 用户表
 * 
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Data
@TableName("users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Integer userId;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 用户状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;

	// 角色
	@TableField(exist = false)
	private RolesEntity role;

	public List<String> getAllRolesName(){
		List<String> rolesName = new ArrayList<>();
		if(this.role != null)
			rolesName.add(this.role.getRoleName().toUpperCase());
		return rolesName;
	}

}
