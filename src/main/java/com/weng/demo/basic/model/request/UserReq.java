package com.weng.demo.basic.model.request;

import com.weng.framework.dao.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


/**
 * 用户信息请求
 *
 * @author 翁荟
 * @date 2019-05-20 10:11:49
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserReq extends BaseEntity<UserReq> {

	private static final long serialVersionUID = 1L;
	
	/**
     * id
     */
	private String id;
    
	/**
     * 账号
     */
	private String account;
    
	/**
     * 密码
     */
	private String password;

	/**
	 * 机构Id
	 */
	private String orgId;
    
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
     * 是否超级管理员
     */
	private Integer isSuperAdmin;
    
	/**
     * 是否删除，1删除，0否
     */
	private Integer isDel;
    
	/**
     * 创建时间
     */
	private Date createTime;
    
	/**
     * 更新时间
     */
	private Date updateTime;
    

}
