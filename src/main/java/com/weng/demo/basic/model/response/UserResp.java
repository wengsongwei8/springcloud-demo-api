package com.weng.demo.basic.model.response;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 返回用户信息
 *
 * @author 翁荟
 * @date 2019-05-20 10:11:49
 *
 */
@Data
@Accessors(chain = true)
public class UserResp implements Serializable {


	private static final long serialVersionUID = 3266803845832685886L;

	private List<String> pris;

	private List<String> roles;

	/**
     * id
     */
	private String id;
	
	
	/**
     * 账号
     */
	private String account;
	
	

	/**
     * 用户名称
     */
	private String userName;
	
	
	/**
     * 电话
     */
	private String phone;
	
	
	/**
     * 邮箱
     */
	private String email;
	
	
	/**
     * 性别，1男，2女
     */
	private Integer sex;
	
	
	/**
     * 头像
     */
	private String avatar;
	
	
	/**
     * 用户类型
     */
	private Integer userType;
	
	


	/**
     * 创建时间
     */
	private Date createTime;
	
	
	/**
     * 更新时间
     */
	private Date updateTime;
	
	
}
