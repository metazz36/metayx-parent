package com.metazz.metayx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metayx.model.acl.Admin;
import com.metazz.metayx.vo.acl.AdminQueryVo;

public interface AdminService extends IService<Admin> {

    // 用户列表
    IPage<Admin> selectPageUser(Page<Admin> pageParam, AdminQueryVo adminQueryVo);

}
