package com.weng.demo.basic.model.request;

import com.weng.framework.dao.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 机构请求
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrgReq extends BaseEntity<OrgReq> {

	private static final long serialVersionUID = 1L;
	
	/**
     * 机构id
     */
	private String id;
    
	/**
     * 父机构id
     */
	private String parentId;

	/**
	 * 父机构ids
	 */
	private String parentIds;

	/**
	 * 父机构ids和本机构Id【冗余】,便于查询本机构和下属机构
	 */
	private String pidsAndOwnerId;
    
	/**
     * 机构代码
     */
	private String orgNum;
    
	/**
     * 机构名称
     */
	private String orgName;

	/**
	 * 机构简介
	 */
	private String orgIntroduction;

	/**
	 * 机构层级
	 */
	private Integer orgLevel;

	/**
	 * 删除的多个id,可以删除多个，中间以,号分隔
	 */
	private String ids;

}
