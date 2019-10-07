package com.weng.demo.basic.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.weng.framework.dao.mybatis.entity.BaseEntity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 机构实体Bean
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("u_org")
public class Org extends BaseEntity<Org> {

    private static final long serialVersionUID = 1L;
	
	
	/**
     * 机构id
     */
	@TableField(value="id")
	private String id;
	
	
	/**
     * 父机构id
     */
	@TableField(value="parent_id")
	private String parentId;
	
	
	/**
     * 父机构ids
     */
	@TableField(value="parent_ids")
	private String parentIds;

	/**
	 * 父机构ids和本机构Id【冗余】
	 */
	@TableField(value="pids_and_owner_id")
	private String pidsAndOwnerId;

	/**
     * 机构代码
     */
	@TableField(value="org_num")
	private String orgNum;
	
	
	/**
     * 机构名称
     */
	@TableField(value="org_name")
	private String orgName;
	
	
	/**
     * 机构简称
     */
	@TableField(value="org_short_name")
	private String orgShortName;
	
	
	/**
     * 机构英文
     */
	@TableField(value="org_name_en")
	private String orgNameEn;
	
	
	/**
     * 机构类型
     */
	@TableField(value="org_type_code")
	private String orgTypeCode;
	
	
	/**
     * 机构层级
     */
	@TableField(value="org_level")
	private Integer orgLevel;
	
	
	/**
     * 机构简介
     */
	@TableField(value="org_introduction")
	private String orgIntroduction;
	
	
	/**
     * 机构图片
     */
	@TableField(value="img")
	private String img;
	
	
	/**
     * 机构详细地址
     */
	@TableField(value="org_address")
	private String orgAddress;
	
	
	/**
     * 机构所属行政区划码
     */
	@TableField(value="area_id")
	private String areaId;
	
	
	/**
     * 机构所属区域
     */
	@TableField(value="area_info")
	private String areaInfo;
	
	
	/**
     * 邮政编码
     */
	@TableField(value="post_num")
	private String postNum;
	
	
	/**
     * 联系人
     */
	@TableField(value="contact_name")
	private String contactName;
	
	
	/**
     * 联系电话
     */
	@TableField(value="contact_mobile")
	private String contactMobile;
	
	
	/**
     * 传真电话
     */
	@TableField(value="contact_fax_mobile")
	private String contactFaxMobile;
	
	
	/**
     * 电子邮箱
     */
	@TableField(value="email")
	private String email;
	
	
	/**
     * 主页地址
     */
	@TableField(value="web_url")
	private String webUrl;
	
	
	/**
     * 排序
     */
	@TableField(value="sort_no")
	private Integer sortNo;
	
	
	/**
     * 备注
     */
	@TableField(value="remark")
	private String remark;
	
	
	/**
     * 是否逻辑删除标识（0-未删除，1-已删除）
     */
	@TableField(value="is_del")
	private String isDel;
	
	
	/**
     * 创建时间
     */
	@TableField(value="create_time",fill = FieldFill.INSERT)
	private Date createTime;
	
	
	/**
     * 更新时间
     */
	@TableField(value="update_time" ,fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
	
}
