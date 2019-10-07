package com.weng.demo.basic.api;

import com.weng.demo.basic.model.request.UserReq;
import com.weng.demo.basic.service.IUserService;
import com.weng.demo.basic.entity.User;
import com.weng.demo.common.config.AppConfig;
import com.weng.framework.common.exception.ResultCodeEnum;
import com.weng.framework.common.utils.security.password.PasswordEncoder;
import com.weng.framework.common.utils.security.password.ShaPasswordEncoder;
import com.weng.framework.core.model.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.weng.framework.web.base.BaseController;
import javax.validation.Valid;
import cn.hutool.core.convert.Convert;


/**
 * 用户信息 前端控制器
 *
 * @author 翁荟
 * @date 2019-05-20 10:11:49
 *
 */  
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户信息api", tags = {"用户信息操作接口"})
public class UserApi extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 功能描述:单个获取用户信息
     */
    @ApiOperation(value = "获取用户信息", notes = "")
    @GetMapping(value = "/get")
    public ResponseData get(String id){
        User user = userService.getById(id);
        if(user == null){
	    return ResponseData.failed("获取失败，用户信息不存在!");
        }
        return success(user);
    }


    /**
     * 条件 分页查询用户信息
     */
    @ApiOperation(value = "分页获取用户信息", notes = "")
    @GetMapping(value = "/list")
    public ResponseData list(String account, String userName, Integer pageNo,Integer pageSize){
		IPage<User> iPage = userService.list(account, userName, pageNo, pageSize);
        return success(iPage);
    }


    /**
	 * 新增用户信息
	 */
	@ApiOperation(value = "新增用户信息", notes = "")
	@PostMapping(value = "/add")
	public ResponseData add(@Valid @RequestBody UserReq userReq){
		User user = Convert.convert(User.class, userReq);
		userService.add(user);
		return success();
	}

	/**
	 * 更新用户信息
	 */
	@ApiOperation(value = "更新用户信息", notes = "")
	@PostMapping(value = "/update")
	public ResponseData update(@Valid @RequestBody UserReq userReq){
		User user = Convert.convert(User.class, userReq);
		int flag = userService.update(user);
		if(flag >0 ) {
			return success(flag);
		}
		return ResponseData.failed(ResultCodeEnum.DATA_IS_NOT_UPDATED.getCode(),"没有数据更新！");
	}


	/**
	 * 删除用户信息
	 */
	@ApiOperation(value = "删除用户信息", notes = "可以删除多个，中间以,号分隔")
	@PostMapping(value = "/delete")
	public ResponseData delete(String ids){
		boolean flag = userService.removeByIds(Arrays.asList(ids.split(",")));
		if(flag){
			return success(ids);
		}else{
			return failed("删除失败");
		}
	}
}
