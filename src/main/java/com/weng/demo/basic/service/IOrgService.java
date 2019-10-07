package com.weng.demo.basic.service;

import com.weng.demo.basic.entity.Org;
import com.baomidou.mybatisplus.extension.service.IService;

import com.weng.demo.basic.model.response.OrgTreeNode;
import com.weng.framework.core.model.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weng.framework.common.exception.GlobalServiceException;
import com.weng.demo.basic.model.request.OrgReq;

import java.util.List;


/**
 * 机构 服务接口
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */  
public interface IOrgService extends IService<Org> {

	/**
	 * 获取树形表格数据
	 * @param name
	 * @param filterId 需要过滤的节点及子结点
	 * @param parentId
	 * @param isExpand
	 * @return
	 * @throws Exception
	 */
	List<OrgTreeNode> getTreeGridData(String name, String parentId, String isExpand, String filterId);

	/**
	 * 新建单个
	 */
	public Org add(Org code) throws GlobalServiceException;


	/**
	 * 更新
	 */
	public int update(Org code) throws GlobalServiceException;

	
	/**
	 * 删除
	 */
	public ResponseData removeByIds(String ids);

	/**
	 *  列表查询base
	 *  <p>
	 *      自定义列表查询：需实现定制化业务，组合查询，多分页查询等
	 */
	IPage<Org> list(Integer pageNo, Integer pageSize, OrgReq orgReq);

}
