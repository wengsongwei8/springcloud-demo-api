package com.weng.demo.basic.service;

import com.weng.demo.basic.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import com.weng.framework.core.model.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weng.framework.common.exception.GlobalServiceException;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户信息 服务接口
 *
 * @author 翁荟
 * @date 2019-05-20 10:11:49
 *
 */  
public interface IUserService extends IService<User> {


	/*
	 * 判断机构是否被使用
	 * @param [orgIds]
	 * @return true表示存在使用的机构，false表示不存在
	 */
	boolean isOrgExists(List<String> orgIds);

	/*
	 * 根据账号查找用户
	 * @param [account]
	 * @return
	 */
	User findUser(String account);


	/*
	 * 更新密码
	 * @param [password, id]
	 * @return
	 */
	int changePwd(@Param("password") String password, @Param("id") String id);

	/**
	 * 新建单个
	 */
	public User add(User code) throws GlobalServiceException;


	/**
	 * 更新
	 */
	public int update(User code) throws GlobalServiceException;

	
	/**
	 * 删除
	 */
	public ResponseData removeByIds(String ids);

	/**
	 *  列表查询base
	 *  <p>
	 *      自定义列表查询：需实现定制化业务，组合查询，多分页查询等
	 */
	IPage<User> list(String account, String userName, Integer pageNo, Integer pageSize);

}
