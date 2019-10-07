package com.weng.demo.basic.api;

import com.weng.demo.basic.model.request.OrgReq;
import com.weng.demo.basic.model.response.OrgTreeNode;
import com.weng.demo.basic.service.IOrgService;
import com.weng.demo.basic.entity.Org;
import com.weng.demo.basic.service.IUserService;
import com.weng.framework.common.exception.ResultCodeEnum;
import com.weng.framework.core.model.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.weng.framework.web.base.BaseController;
import javax.validation.Valid;
import cn.hutool.core.convert.Convert;


/**
 * 机构 前端控制器
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */  
@Slf4j
@RestController
@RequestMapping("/org")
@Api(value = "机构api", tags = {"机构操作接口"})
public class OrgApi extends BaseController {
    @Autowired
    private IOrgService orgService;
	@Autowired
	private IUserService userService;

    /**
     * 功能描述:单个获取机构
     */
    @ApiOperation(value = "获取机构", notes = "")
    @GetMapping(value = "/get")
    public ResponseData get(String id){
        Org org = orgService.getById(id);
        if(org == null){
	    return ResponseData.failed("获取失败，机构不存在!");
        }
        return success(org);
    }


    /**
     * 条件 分页查询机构
     */
    @ApiOperation(value = "分页获取机构", notes = "")
    @GetMapping(value = "/list")
    public ResponseData list(Integer pageNo,Integer pageSize, OrgReq orgReq){
		IPage<Org> iPage = orgService.list(pageNo, pageSize, orgReq);
        return success(iPage);
    }


    /**
	 * 新增机构
	 */
	@ApiOperation(value = "新增机构", notes = "")
	@PostMapping(value = "/add")
	public ResponseData add(@Valid @RequestBody OrgReq orgReq){
		Org org = Convert.convert(Org.class, orgReq);
		orgService.add(org);
		return success();
	}

	/**
	 * 更新机构
	 */
	@ApiOperation(value = "更新机构", notes = "")
	@PostMapping(value = "/update")
	public ResponseData update(@Valid @RequestBody OrgReq orgReq){
		Org org = Convert.convert(Org.class, orgReq);
		int flag = orgService.update(org);
		if(flag >0 ) {
			return success(flag);
		}
		return ResponseData.failed(ResultCodeEnum.DATA_IS_NOT_UPDATED.getCode(),"没有数据更新！");
	}


	/**
	 * 删除机构
	 */
	@ApiOperation(value = "删除机构", notes = "ids 可以删除多个，中间以,号分隔")
	@PostMapping(value = "/delete")
	public ResponseData delete(@Valid @RequestBody OrgReq orgReq){
		if(StringUtils.isEmpty(orgReq.getIds())){
			return failed("删除失败,删除id不能为空!");
		}
		List<String> orgIds = Arrays.asList(orgReq.getIds().split(","));
		boolean isOrgUsed = userService.isOrgExists(orgIds);
		if(isOrgUsed){
			return failed("选中的机构已经被使用，不能删除!");
		}
		boolean flag = orgService.removeByIds(orgIds);
		if(flag){
			return success(orgReq.getIds());
		}else{
			return failed("删除失败");
		}
	}


	@ResponseBody
	@RequestMapping( value = "/getTreeGridData" ,method=RequestMethod.GET)
	@ApiOperation(value = "获取树形表格数据", notes = "获取树形表格数据")
	public ResponseData getTreeGridData(String name,String parentId,String isExpand,String filterId) throws Exception{

		List<OrgTreeNode> treeDatas = this.orgService.getTreeGridData(name, parentId, isExpand, filterId);
		addDefaultParentNode(treeDatas);
		return ResponseData.success(treeDatas);
	}

	// 添加默认父级机构为无的情况
	public void addDefaultParentNode(List<OrgTreeNode> treeDatas){
		treeDatas = treeDatas==null ?new ArrayList<>() : treeDatas;
		OrgTreeNode orgTreeNode = new OrgTreeNode();
		orgTreeNode.setId("-1");
		orgTreeNode.setKey("-1");
		orgTreeNode.setName("无");
		orgTreeNode.setLabel("无");
		treeDatas.add(orgTreeNode);
	}
}
