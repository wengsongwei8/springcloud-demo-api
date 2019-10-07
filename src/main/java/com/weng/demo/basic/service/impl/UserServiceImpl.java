package com.weng.demo.basic.service.impl;

import com.weng.demo.basic.entity.User;
import com.weng.demo.basic.mapper.UserMapper;
import com.weng.demo.basic.service.IUserService;
import com.weng.framework.common.utils.security.password.PasswordEncoder;
import com.weng.framework.common.utils.security.password.ShaPasswordEncoder;
import com.weng.framework.core.model.ResponseData;
import com.weng.framework.dao.mybatis.service.BaseServiceImpl;
import com.weng.framework.common.exception.GlobalServiceException;
import com.weng.framework.common.exception.ResultCodeEnum;
import com.weng.framework.dao.mybatis.model.page.PageModel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weng.demo.common.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


/**
 * 用户信息 服务实现类
 *
 * @author 翁荟
 * @date 2019-05-20 10:11:49
 *
 */ 
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean isOrgExists(List<String> orgIds) {
		QueryWrapper<User> qw = new QueryWrapper<User>();
		qw.in("org_id",orgIds);
		int count =  userMapper.selectCount(qw);
		return count>0 ? true : false;
	}

	@Override
	public User findUser(String account) {

		return userMapper.findByUserName(account);
	}

	@Override
	public int changePwd(String password, String id) {
		return userMapper.changePwd(password,id);
	}

	/**
	 * 分页查询用户信息
	 * @return
	 */
	@Override
	public IPage<User> list(String account, String userName,Integer pageNo,Integer pageSize) {
		QueryWrapper<User> qw = new QueryWrapper<User>();
		IPage<User> page = new PageModel<User>(pageNo,pageSize);

		if(!StringUtils.isEmpty(userName)){
			qw.like("user_name", userName);
		}
		if(!StringUtils.isEmpty(account)){
			qw.like("account", account);
		}

		return userMapper.selectPage(page,qw);
	}


	/**
	 * 获取单个用户信息
	 * @param id
	 * @return
	 */
	@Override
	public User getById(Serializable id) {
		return super.getById(id);
	}

	/**
	 * 新增单个用户信息
	 * @param user
	 * @return
	 */
	@Override
	public User add(User user) throws GlobalServiceException {
		PasswordEncoder passwordEncoder = new ShaPasswordEncoder();
		if(user!=null && StringUtils.isEmpty(user.getPassword())){
			user.setPassword(passwordEncoder.encode(AppConfig.DEFAULT_PASSWORD));
		}
		userMapper.insert(user);
		return user;
	}

	/**
	 * 更新单个用户信息
	 * @param user
	 * @return
	 * @throws GlobalServiceException
	 */
	@Override
	public int update(User user) throws GlobalServiceException {
		
		return userMapper.updateById(user);

	}


	@Override
	public ResponseData removeByIds(String ids) {
		String[] userIds = ids.split(",");
		boolean flag = this.removeByIds(Arrays.asList(userIds));
		if (flag) {
			return ResponseData.success();
		} else {
			throw new GlobalServiceException(ResultCodeEnum.DATABASE_OPERATE_EXCEPTION.getCode(), "删除用户信息：" + Arrays.asList(userIds).toString() + ResultCodeEnum.DATABASE_OPERATE_EXCEPTION.getMessage());
		}
	}

}
