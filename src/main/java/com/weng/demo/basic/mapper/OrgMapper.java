package com.weng.demo.basic.mapper;

import com.weng.demo.basic.entity.Org;
import com.weng.framework.dao.mybatis.mapper.BaseMapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
 
/**
 * 机构 Mapper 接口
 *
 * @author 翁荟
 * @date 2019-05-20 10:21:19
 *
 */ 
@Mapper
@Repository
public interface OrgMapper extends BaseMapper<Org> {


}
