package com.weng.demo.basic.service.impl;

import com.weng.demo.basic.model.request.OrgReq;
import com.weng.demo.basic.model.response.OrgTreeNode;
import com.weng.demo.basic.service.IOrgService;
import com.weng.framework.common.model.component.tree.TreeGridBuilder;
import com.weng.framework.common.model.component.tree.TreeNode;
import com.weng.demo.basic.entity.Org;
import com.weng.demo.basic.mapper.OrgMapper;
import com.weng.framework.core.model.ResponseData;
import com.weng.framework.dao.mybatis.service.BaseServiceImpl;
import com.weng.framework.common.exception.GlobalServiceException;
import com.weng.framework.common.exception.ResultCodeEnum;
import com.weng.framework.dao.mybatis.model.page.PageModel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


/**
 * 机构 服务实现类
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */ 
@Slf4j
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements IOrgService {
	@Autowired
	private OrgMapper orgMapper;


	public List<OrgTreeNode> getTreeGridData(String name, String parentId, String isExpand, String filterId){

		Org Org = new Org();
		QueryWrapper<Org> wrapper = new QueryWrapper<Org>(Org);
		if(!StringUtils.isEmpty(name)){
			wrapper.like("org_name", name);
		}

		List<Org> data = orgMapper.selectList(wrapper);
		List<OrgTreeNode> list = new TreeGridBuilder<Org, OrgTreeNode>(data)
				.build(new OrgComparator(),new OrgTreeNode(), false);
		if ("1".equals(isExpand)) {
			TreeNode.expandAllNodes(list);
		}
		if(StringUtils.isNotEmpty(filterId)){
			TreeNode.filterNodes(list,filterId);
		}
		return list;

	}




	/**
	 * 分页查询机构
	 * @return
	 */
	@Override
	public IPage<Org> list(Integer pageNo,Integer pageSize, OrgReq orgReq) {
		QueryWrapper<Org> qw = new QueryWrapper<Org>();
		IPage<Org> page = new PageModel<Org>(pageNo,pageSize);
		qw.orderByAsc("org_level");
		if(orgReq != null){
			if(StringUtils.isNotEmpty(orgReq.getParentIds())){
				qw.like("parent_ids", orgReq.getParentIds());
			}
			if(StringUtils.isNotEmpty(orgReq.getOrgName())){
				qw.like("org_name", orgReq.getOrgName());
			}
		}
		return orgMapper.selectPage(page,qw);
	}


	/**
	 * 获取单个机构
	 * @param id
	 * @return
	 */
	@Override
	public Org getById(Serializable id) {
		return super.getById(id);
	}

	/**
	 * 新增单个机构
	 * @param org
	 * @return
	 */
	@Override
	public Org add(Org org) throws GlobalServiceException {
		
		orgMapper.insert(org);

		// 增加 父机构ids和本机构Id【冗余】
		org.setPidsAndOwnerId(getPidsAndOwnerId(org));
		orgMapper.updateById(org);

		return org;
	}
	private String getPidsAndOwnerId(Org org){
		String pidsAndOwnerId = "";
		if(org!=null && StringUtils.isNotEmpty(org.getParentIds())){
			pidsAndOwnerId = org.getParentIds() + "," + org.getId();
		}
		return pidsAndOwnerId;
	}

	/**
	 * 更新单个机构
	 * @param org
	 * @return
	 * @throws GlobalServiceException
	 */
	@Override
	public int update(Org org) throws GlobalServiceException {

		// 增加 父机构ids和本机构Id【冗余】
		org.setPidsAndOwnerId(getPidsAndOwnerId(org));
		return orgMapper.updateById(org);
	}


	@Override
	public ResponseData removeByIds(String ids) {
		String[] orgIds = ids.split(",");
		boolean flag = this.removeByIds(Arrays.asList(orgIds));
		if (flag) {
			return ResponseData.success();
		} else {
			throw new GlobalServiceException(ResultCodeEnum.DATABASE_OPERATE_EXCEPTION.getCode(), "删除机构：" + Arrays.asList(orgIds).toString() + ResultCodeEnum.DATABASE_OPERATE_EXCEPTION.getMessage());
		}
	}


	private class OrgComparator implements Comparator<Org> {
		@Override
		public int compare(Org o1, Org o2) {
			return 0;
		}
	}
}
